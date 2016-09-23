package com.savelife.mvc.rest;

import com.savelife.mvc.model.messaging.device.DeviceMessage;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * user rest controller to receiving get requests
 */
@RestController
@RequestMapping(value = {"/rest/user"})
public class UserGet {

    private static Logger logger = Logger.getLogger(UserGet.class.getName());

    /*
    * CRUD user service
    * */
    @Autowired
    UserService userService;

    @GetMapping(params = {"role=ambulance"})
    public Callable<ResponseEntity<String>> get(String role, @RequestParam("token") String token) {
        return () -> {
            /*userService.findAllByRole(role).forEach(v -> System.out.println(v.getToken()));*/
            logger.info("Received role= " + role);
            System.out.println(userService.exist(token));
            return new ResponseEntity(HttpStatus.OK);
        };
    }
}
