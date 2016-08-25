package com.savelife.mvc.controller.rest;

import com.google.gson.Gson;
import com.savelife.mvc.apis.converter.Converter;
import com.savelife.mvc.model.massaging.device.DeviceMassage;
import com.savelife.mvc.model.massaging.server.Data;
import com.savelife.mvc.model.massaging.server.ServerMassage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.detection.DetectService;
import com.savelife.mvc.service.routing.RoutingService;
import com.savelife.mvc.service.sender.SenderService;
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

    @RequestMapping(value = {"/rest/send/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> receive(@RequestBody DeviceMassage deviceMassage) {
        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {
                String role = deviceMassage.getRole();
                Double deviceCurrentLat = deviceMassage.getCurrentLat();
                Double deviceCurrentLon = deviceMassage.getCurrentLon();
                if (role != null
                        & deviceCurrentLat != null
                        & deviceCurrentLon != null) {
                    if (role.equals("ambulance")) {
                        double radius = 100;
                        try {
                            System.out.println(deviceMassage.toString());

                            Converter<List<UserEntity>, List<String>> converter = (entities) -> {
                                List<String> converted = new ArrayList<>();

                                entities.forEach((k) -> {
                                    ServerMassage m = new ServerMassage();
                                    m.setTo(k.getToken());
                                    Data d = new Data();
                                    d.setMassageBody("Hi, would you like to rebuild your road path?");

                                    /*build path */
                                    d.setPath(routingService.getRoute(Double.parseDouble(k.getCurrentLatitude())
                                            , Double.parseDouble(k.getCurrentLongitude())
                                            , Double.parseDouble(k.getDestinationLatitude())
                                            , Double.parseDouble(k.getDestinationLongitude())));
                                    m.setData(d);

                                    /* convert into JSON format */
                                    Gson gson = new Gson();
                                    converted.add(gson.toJson(m));
                                });
                                return converted;
                            };

                            senderService.send(converter.convert(detectionService.detect(radius, deviceCurrentLat, deviceCurrentLon, userService.findAllByRole("driver"))))
                                    .forEach((v) -> System.out.println(v));
                            return new ResponseEntity<Void>(HttpStatus.OK);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                        }

                    } else if (role.equals("driver")) {
                        /*update driver position */
                        String oldToken = deviceMassage.getOldToken();

                        UserEntity userEntity = userService.findUserByToken(oldToken);
                        userEntity.setCurrentLatitude(String.valueOf(deviceCurrentLat));
                        userEntity.setCurrentLongitude(String.valueOf(deviceCurrentLon));

                        userService.update(userEntity);
                        return new ResponseEntity<Void>(HttpStatus.OK);
                    } else if (role.equals("person")) {

                        return new ResponseEntity<Void>(HttpStatus.OK);
                    }

                    /* no one correct role */
                    return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
                } else {
                    /*invalid massage fields */
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }
            }
        };

    }
}
