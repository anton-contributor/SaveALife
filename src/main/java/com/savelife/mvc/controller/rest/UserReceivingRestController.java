package com.savelife.mvc.controller.rest;

import com.google.gson.Gson;
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
        return () -> {
            String role = deviceMassage.getRole();
            if (role != null) {
                if (role.equals("ambulance")) {
                    try {
//                        senderService.send(convert(defineList(deviceMassage.getCurrentLat(), deviceMassage.getCurrentLon(), userService.findAllByRole(role))));
                        convert(defineList(deviceMassage.getCurrentLat(), deviceMassage.getCurrentLon(), userService.findAllByRole("driver")))
                               .forEach((v) -> System.out.println(v));

                        senderService.send(convert(defineList(deviceMassage.getCurrentLat(), deviceMassage.getCurrentLon(), userService.findAllByRole("driver"))));
                        return new ResponseEntity<Void>(HttpStatus.OK);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                    }

                } else if (role.equals("driver")) {

                    String oldToken = deviceMassage.getOldToken();

                    UserEntity userEntity = userService.findUserByToken(oldToken);
                    userEntity.setCurrentLatitude(String.valueOf(deviceMassage.getCurrentLat()));
                    userEntity.setCurrentLongitude(String.valueOf(deviceMassage.getCurrentLon()));

                    userService.update(userEntity);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                } else if (role.equals("person")) {

                    return new ResponseEntity<Void>(HttpStatus.OK);
                }
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            } else {
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
        };
    }

    /*
    * defineList devices which included into ambulance radius
    * */
    private List<UserEntity> defineList(Double centerX, Double centerY, List<UserEntity> entities) {
        List<UserEntity> defined = new ArrayList<>();

        entities.forEach((k) -> {
            if (detectionService.detect(
                    100.0,
                    centerX,
                    centerY,
                    Double.parseDouble(k.getCurrentLatitude()),
                    Double.parseDouble(k.getCurrentLongitude()))) {
                defined.add(k);
            }
        });
        return defined;
    }
    private List<String> convert(List<UserEntity> entities){
        List<String> converted = new ArrayList<>();

        entities.forEach((k) -> {
            ServerMassage m = new ServerMassage();
            m.setTo(k.getToken());
            Data d = new Data();
            d.setMassageBody("Hi, would you like to rebuild your road path?");

            d.setPath(routingService.getRoute(Double.parseDouble(k.getCurrentLatitude())
                    , Double.parseDouble(k.getCurrentLongitude())
                    , Double.parseDouble(k.getDestinationLatitude())
                    , Double.parseDouble(k.getDestinationLongitude()) ));
            m.setData(d);

            Gson gson = new Gson();

            converted.add(gson.toJson(m));
        });

        return converted;
    }

}
