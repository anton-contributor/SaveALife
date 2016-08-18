package com.savelife.mvc.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savelife.mvc.model.massaging.device.DeviceMassage;
import com.savelife.mvc.model.massaging.server.Data;
import com.savelife.mvc.model.massaging.server.ServerMassage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.detection.DetectService;
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

import static com.savelife.mvc.model.user.singleton.UserRoleContainer.getRole;

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

    @RequestMapping(value = {"/rest/send/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> receive(@RequestBody DeviceMassage massage) {
        return () -> {
            String role = massage.getRole();
            if (role != null) {
                if (getRole(role) == 1) {
                   senderService.echo(convert(define(massage.getCurrentLat(),massage.getCurrentLon(), userService.findAllByRole(role))));
                }
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
        };
    }

    /*
    * define devices which included into ambulance radius
    * */
    private List<UserEntity> define(Double centerX, Double centerY,List<UserEntity> entities) {
        List<UserEntity> defined = new ArrayList<>();

        entities.forEach((k)->{
            if(detectionService.detect(
                    100.0,
                    centerX,
                    centerY,
                    Double.valueOf(k.getLatitude()),
                    Double.valueOf(k.getLongitude()))){
                defined.add(k);
            }
        });
        return defined;
    }

    /*
    * convert all defined devices to sending them to drivers
    * */
    private List<String> convert(List<UserEntity> entities){
        List<String> converted = new ArrayList<>();

        entities.forEach((k)->{
            ServerMassage m = new ServerMassage();
            m.setTo(k.getToken());
            Data d = new Data();
            d.setMassage("Hi, would you like to rebuild your road path?");
            m.setData(d);
            ObjectMapper mapper = new ObjectMapper();
            try {
                converted.add(mapper.writeValueAsString(m));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return converted;
    }

}
