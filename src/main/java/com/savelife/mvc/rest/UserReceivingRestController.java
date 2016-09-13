package com.savelife.mvc.rest;

import com.google.gson.Gson;
import com.savelife.mvc.apis.converter.Converter;
import com.savelife.mvc.model.messaging.device.DeviceMassage;
import com.savelife.mvc.model.messaging.server.Data;
import com.savelife.mvc.model.messaging.server.ServerMassage;
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
    public Callable<ResponseEntity<Void>> receive(@RequestBody DeviceMassage deviceMassage) {
        /* logger */
        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {
                String role = deviceMassage.getRole();

                if (!Objects.isNull(role)
                        && !Objects.isNull(deviceMassage.getCurrentLat())
                        && !Objects.isNull(deviceMassage.getCurrentLon())) {
                    switch (role) {
                        case "ambulance":
                            /* find user by token */
                            UserEntity ambulance = userService.findUserByToken(deviceMassage.getCurrentToken());
                            if (!Objects.isNull(deviceMassage.getCurrentToken()) && !userService.exist(deviceMassage.getCurrentToken())) {
                                /* save ambulance */
                                UserEntity newAmbulance = new UserEntity(
                                        deviceMassage.getCurrentToken()
                                        , true
                                        , deviceMassage.getCurrentLat()
                                        , deviceMassage.getCurrentLon()
                                        , deviceMassage.getDestinationLat()
                                        , deviceMassage.getDestinationLon()
                                        , userRoleService.findRoleByName(role)
                                );
                                userService.save(newAmbulance);
                            } else {
                                /*update ambulance */
                                ambulance.setCurrentLatitude(deviceMassage.getCurrentLat());
                                ambulance.setCurrentLongitude(deviceMassage.getCurrentLon());
                                ambulance.setDestinationLatitude(deviceMassage.getDestinationLat());
                                ambulance.setDestinationLongitude(deviceMassage.getDestinationLon());
                                ambulance.setEnable(deviceMassage.isEnable());
                                ambulance.setToken(deviceMassage.getCurrentToken());

                                userService.update(ambulance);
                            }
                            try {
                            /* check the ambulance status(in race or complete)*/
                                if (!Objects.isNull(deviceMassage.isEnable()) && deviceMassage.isEnable()) {

                                    userService.setAllUsersUnable();
                                    return new ResponseEntity<Void>(HttpStatus.OK);
                                }
                                /* radius of the detection */
                                double radius = 100;
                                Converter<List<UserEntity>, List<String>> converter = (entities) -> {
                                    List<String> converted = new ArrayList<>();

                                    entities.forEach((k) -> {
                                        ServerMassage m = new ServerMassage();
                                        m.setTo(k.getToken());
                                        Data d = new Data();
                                        d.setMassageBody("Hi, would you like to rebuild your road path?");
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
                                                        , deviceMassage.getCurrentLat()
                                                        , deviceMassage.getCurrentLon()
                                                        , userService.findAllByRole("driver"))));
                                return new ResponseEntity<Void>(HttpStatus.OK);
                            } catch (NullPointerException e) {
                                /*logger */
                                e.printStackTrace();
                                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                            }

                        case "driver":
                            /*update driver position */
                            String currentToken = deviceMassage.getCurrentToken();
                            UserEntity userEntity = userService.findUserByToken(currentToken);

                            if (!Objects.isNull(userEntity) && !userService.exist(currentToken)) {
                            /* save driver */
                                UserEntity newUser = new UserEntity(
                                        currentToken
                                        , true
                                        , deviceMassage.getCurrentLat()
                                        , deviceMassage.getCurrentLon()
                                        , deviceMassage.getDestinationLat()
                                        , deviceMassage.getDestinationLon()
                                        , userRoleService.findRoleByName(role)
                                );
                                userService.save(newUser);
                                return new ResponseEntity<Void>(HttpStatus.CREATED);
                            } else {
                                /*update driver*/
                                userEntity.setCurrentLatitude(deviceMassage.getCurrentLat());
                                userEntity.setCurrentLongitude(deviceMassage.getCurrentLon());
                                userEntity.setDestinationLatitude(deviceMassage.getDestinationLat());
                                userEntity.setDestinationLongitude(deviceMassage.getDestinationLon());
                                userEntity.setEnable(deviceMassage.isEnable());
                                userEntity.setToken(deviceMassage.getCurrentToken());

                                userService.update(userEntity);
                                return new ResponseEntity<Void>(HttpStatus.OK);
                            }
                        case "person":

                            UserEntity person = userService.findUserByToken(deviceMassage.getCurrentToken());
                            if (!Objects.isNull(deviceMassage.getCurrentToken()) && !userService.exist(deviceMassage.getCurrentToken())) {
                                 /*update person */
                                person.setCurrentLatitude(deviceMassage.getCurrentLat());
                                person.setCurrentLongitude(deviceMassage.getCurrentLon());
                                person.setDestinationLatitude(deviceMassage.getDestinationLat());
                                person.setDestinationLongitude(deviceMassage.getDestinationLon());
                                person.setEnable(deviceMassage.isEnable());
                                person.setToken(deviceMassage.getCurrentToken());

                                userService.update(person);
                            }else {
                                /* save driver */
                                UserEntity newUser = new UserEntity(
                                          deviceMassage.getCurrentToken()
                                        , true
                                        , deviceMassage.getCurrentLat()
                                        , deviceMassage.getCurrentLon()
                                        , deviceMassage.getDestinationLat()
                                        , deviceMassage.getDestinationLon()
                                        , userRoleService.findRoleByName(role)
                                );
                                userService.save(newUser);
                            }
                            double radius = 1000.0;//radius of the distance to notify the devices

                            Converter<List<UserEntity>, List<String>> converter = (entities) -> {
                                List<String> converted = new ArrayList<>();

                                entities.forEach((k) -> {
                                    ServerMassage m = new ServerMassage();
                                    m.setTo(k.getToken());
                                    Data d = new Data();
                                    d.setMassageBody("Need a help due to the " + deviceMassage.getMessage());
                                    d.setLatitude(deviceMassage.getCurrentLat());
                                    d.setLongitude(deviceMassage.getCurrentLon());
                                    /* convert into JSON format */
                                    Gson gson = new Gson();
                                    converted.add(gson.toJson(m));
                                });
                                return converted;
                            };
                            /* send messages to everyone */
                            senderService.send(
                                    converter.convert(
                                            detectionService.detect(
                                                    radius,
                                                    deviceMassage.getCurrentLat(),
                                                    deviceMassage.getCurrentLon(),
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
