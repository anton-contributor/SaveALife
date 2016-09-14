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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Receiving devices data controller
 */
@RestController
public class UserReceivingRestController {

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
        /* logger */
        System.out.println(deviceMessage.toString());
        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {
                String role = deviceMessage.getRole();

                if (role != null
                        && deviceMessage.getCurrentLat() != null
                        && deviceMessage.getCurrentLon()!= null) {
                    switch (role) {
                        case "ambulance":
                            /* find user by token */

                            try {
                            /* check the ambulance status(in race or complete)*/
                                if (!deviceMessage.isEnable() && deviceMessage.isEnable()) {

                                    userService.setAllUsersUnable();
                                    return new ResponseEntity<Void>(HttpStatus.OK);
                                }
                                /* radius of the detection */
                                double radius = 100;
                                Converter<List<UserEntity>, List<String>> converter = (entities) -> {
                                    List<String> converted = new ArrayList<>();

                                    entities.forEach((k) -> {
                                        ServerMessage m = new ServerMessage();
                                        m.setTo(k.getToken());
                                        Data d = new Data();
                                        d.setMessageBody("Hi, would you like to rebuild your road path?");
                                    /* build path */
                                        d.setPath(routingService.getRoute(
                                                k.getCurrentLatitude()
                                                , k.getCurrentLongitude()
                                                , k.getDestinationLatitude()
                                                , k.getDestinationLongitude()));
                                        m.setData(d);
                                    /* convert into JSON format */
                                        Gson gson = new Gson();
                                        converted.add(gson.toJson(m));
                                    });
                                    return converted;
                                };
                                senderService.send(
                                        converter.convert(
                                                detectionService.detect(
                                                        radius
                                                        , deviceMessage.getCurrentLat()
                                                        , deviceMessage.getCurrentLon()
                                                        , userService.findAllByRole("driver"))));
                                return new ResponseEntity<Void>(HttpStatus.OK);
                            } catch (NullPointerException e) {
                                /*logger */
                                e.printStackTrace();
                                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                            }

                        case "driver":

                            String currentToken = deviceMessage.getCurrentToken();

                            if (currentToken != null && !userService.exist(currentToken)) {
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
                                userService.save(newUser);
                                return new ResponseEntity<Void>(HttpStatus.CREATED);
                            } else {
                                UserEntity userEntity = userService.findUserByToken(currentToken);
                                /*update driver*/
                                userEntity.setCurrentLatitude(deviceMessage.getCurrentLat());
                                userEntity.setCurrentLongitude(deviceMessage.getCurrentLon());
                                userEntity.setDestinationLatitude(deviceMessage.getDestinationLat());
                                userEntity.setDestinationLongitude(deviceMessage.getDestinationLon());
                                userEntity.setEnable(deviceMessage.isEnable());
                                userEntity.setToken(deviceMessage.getCurrentToken());

                                userService.update(userEntity);
                                return new ResponseEntity<Void>(HttpStatus.OK);
                            }
                        case "person":

                            if (deviceMessage.getCurrentToken() != null && userService.exist(deviceMessage.getCurrentToken())) {
                                 /*update person */
                                UserEntity person = userService.findUserByToken(deviceMessage.getCurrentToken());
                                person.setCurrentLatitude(deviceMessage.getCurrentLat());
                                person.setCurrentLongitude(deviceMessage.getCurrentLon());
                                person.setDestinationLatitude(deviceMessage.getDestinationLat());
                                person.setDestinationLongitude(deviceMessage.getDestinationLon());
                                person.setEnable(deviceMessage.isEnable());
                                person.setToken(deviceMessage.getCurrentToken());

                                userService.update(person);
                            }else {
                                /* save driver */
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
                            }
                            double radius = 1000.0;//radius of the distance to notify the devices

                            System.out.println("converting ");
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
                                    /* convert into JSON format */
                                    Gson gson = new Gson();
                                    converted.add(gson.toJson(m));
                                    System.out.println(m.toString());
                                });
                                return converted;
                            };
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
                    /*logger */
                    /* no one correct role */
                    return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
                } else {
                    /*logger */
                    /*invalid massage fields */
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }
            }
        };

    }
}
