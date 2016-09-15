package com.savelife.mvc.rest;

import com.google.gson.Gson;
import com.savelife.mvc.apis.converter.Converter;
import com.savelife.mvc.model.messaging.device.DeviceMessage;
import com.savelife.mvc.model.messaging.server.Data;
import com.savelife.mvc.model.messaging.server.ServerMessage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.detection.DetectService;
import com.savelife.mvc.service.routing.RoutingService;
import com.savelife.mvc.service.sender.SenderService;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * Receiving devices data controller
 */
@RestController
public class UserReceivingRestController {

    /* initialization logger */
    private static Logger logger = Logger.getLogger(UserReceivingRestController.class.getName());

    /*
    * send to android
    * */
    @Autowired
    SenderService senderService;

    /*
    * CRUD user service
    * */
    @Autowired
    UserService userService;

    /*
    * detection service
    * */
    @Autowired
    DetectService detectionService;

    /*
    * route service
    * */
    @Autowired
    RoutingService routingService;

    /*
    * user role service
    * */
    @Autowired
    UserRoleService userRoleService;


    @RequestMapping(value = {"/rest/send/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> receive(@RequestBody DeviceMessage deviceMessage) {
        logger.info("Received " + deviceMessage);
        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {
                String role = deviceMessage.getRole();
                if (Objects.nonNull(role)
                        && Objects.nonNull(deviceMessage.getCurrentLat())
                        && Objects.nonNull(deviceMessage.getCurrentLon())) {
                    switch (role) {
                        case "ambulance":
                             /*find user by token*/
                            try {
                            /* check the ambulance status(in race or complete)*/
                                if (!deviceMessage.isEnable() && deviceMessage.isEnable()) {
                                    logger.info("Make all users unable");
                                    userService.setAllUsersUnable();
                                    return new ResponseEntity<Void>(HttpStatus.OK);
                                }
                                 /*radius of the detection*/
                                double radius = 100;
                                Converter<List<UserEntity>, List<String>> converter = (entities) -> {
                                    List<String> converted = new ArrayList<>();

                                    entities.forEach((k) -> {
                                        ServerMessage m = new ServerMessage();
                                        m.setTo(k.getToken());
                                        Data d = new Data();
                                        d.setMessageBody("Hi, would you like to rebuild your path?");
                                        /*build path*/
                                        d.setPath(routingService.getRoute(
                                                k.getCurrentLatitude()
                                                , k.getCurrentLongitude()
                                                , k.getDestinationLatitude()
                                                , k.getDestinationLongitude()));
                                        m.setData(d);
                                        /*convert into JSON format*/
                                        Gson gson = new Gson();
                                        logger.info("Converting and adding " + m + " to json");
                                        converted.add(gson.toJson(m));
                                    });
                                    return converted;
                                };
                                logger.info("Sending the massages to converted  drivers ");

                                senderService.send(
                                        converter.convert(
                                                detectionService.detect(
                                                        radius
                                                        , deviceMessage.getCurrentLat()
                                                        , deviceMessage.getCurrentLon()
                                                        , userService.findAllByRole("driver"))));
                                return new ResponseEntity<Void>(HttpStatus.OK);
                            } catch (NullPointerException e) {
                                logger.warning("Warning." + e);
                                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                            }

                        case "driver":
                            logger.info("Inside of the driver ");
                            String currentToken = deviceMessage.getCurrentToken();

                            if (Objects.nonNull(currentToken) && !userService.exist(currentToken)) {
                             /* save driver */
                                UserEntity newUser = new UserEntity(
                                        currentToken
                                        , true
                                        , deviceMessage.getCurrentLat()
                                        , deviceMessage.getCurrentLon()
                                        , deviceMessage.getDestinationLat()
                                        , deviceMessage.getDestinationLon()
                                        , userRoleService.findRoleByName(role)
                                );
                                logger.info("Saving user " + newUser);
                                userService.save(newUser);
                                return new ResponseEntity<Void>(HttpStatus.CREATED);
                            } else {
                                UserEntity userEntity = userService.findUserByToken(currentToken);
                                logger.info("Updating user " + userEntity);
                                /*update driver*/
                                userEntity.setCurrentLatitude(deviceMessage.getCurrentLat());
                                userEntity.setCurrentLongitude(deviceMessage.getCurrentLon());
                                userEntity.setDestinationLatitude(deviceMessage.getDestinationLat());
                                userEntity.setDestinationLongitude(deviceMessage.getDestinationLon());
                                userEntity.setEnable(deviceMessage.isEnable());
                                userEntity.setToken(deviceMessage.getCurrentToken());

                                userService.update(userEntity);
                                logger.info("Updated user " + userEntity);
                                return new ResponseEntity<Void>(HttpStatus.OK);
                            }
                        case "person":
                            logger.info("Inside of the person ");
                            if (Objects.nonNull(deviceMessage.getCurrentToken()) && userService.exist(deviceMessage.getCurrentToken())) {
                                /* update person*/

                                UserEntity person = userService.findUserByToken(deviceMessage.getCurrentToken());
                                logger.info("Updating user " + person);
                                person.setCurrentLatitude(deviceMessage.getCurrentLat());
                                person.setCurrentLongitude(deviceMessage.getCurrentLon());
                                person.setDestinationLatitude(deviceMessage.getDestinationLat());
                                person.setDestinationLongitude(deviceMessage.getDestinationLon());
                                person.setEnable(deviceMessage.isEnable());
                                person.setToken(deviceMessage.getCurrentToken());

                                userService.update(person);
                                logger.info("Updated " + person);
                            } else {
                                 /*save driver*/
                                UserEntity newUser = new UserEntity(
                                        deviceMessage.getCurrentToken()
                                        , true
                                        , deviceMessage.getCurrentLat()
                                        , deviceMessage.getCurrentLon()
                                        , deviceMessage.getDestinationLat()
                                        , deviceMessage.getDestinationLon()
                                        , userRoleService.findRoleByName(role)
                                );
                                userService.save(newUser);
                                logger.info("Saved user " + newUser);
                            }
                            double radius = 1000.0;//radius of the distance to notify the devices

                            Converter<List<UserEntity>, List<String>> converter = (entities) -> {
                                List<String> converted = new ArrayList<>();

                                entities.forEach((k) -> {
                                    ServerMessage m = new ServerMessage();
                                    m.setTo(k.getToken());
                                    Data d = new Data();
                                    System.out.println(deviceMessage.getMessage());
                                    d.setMessageBody("Need a help due to the " + deviceMessage.getMessage());
                                    d.setLatitude(deviceMessage.getCurrentLat());
                                    d.setLongitude(deviceMessage.getCurrentLon());

                                    m.setData(d);
                                    logger.info("Converting and adding " + m + " to json");
                                    /* convert into JSON format*/
                                    Gson gson = new Gson();
                                    converted.add(gson.toJson(m));
                                    logger.info("Converted and ended " + gson.toJson(m));
                                });
                                return converted;
                            };
                            logger.info("Sending massages to everyone ");
                            /* send messages to everyone */
                            senderService.send(
                                    converter.convert(
                                            detectionService.detect(
                                                    radius,
                                                    deviceMessage.getCurrentLat(),
                                                    deviceMessage.getCurrentLon(),
                                                    userService.findAllUsers())));// everyone
                            return new ResponseEntity<Void>(HttpStatus.OK);
                    }
                    logger.info("Not correct role");
                    return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
                } else {
                    logger.info("Invalid fields ");
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }
            }
        };

    }
}
