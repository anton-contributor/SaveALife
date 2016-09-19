package com.savelife.mvc.rest;

import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Callable<ResponseEntity<String>> get(String role) {
        return () -> {
            /*userService.findAllByRole(role).forEach(v -> System.out.println(v.getToken()));*/
            logger.info("Received role= " + role);
            return new ResponseEntity(HttpStatus.OK);
        };
    }
}
