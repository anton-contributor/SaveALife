package com.savelife.mvc.rest;

import com.savelife.mvc.model.messaging.device.DeviceMassage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.Callable;

/*
* Registration user rest controller
*
* */
@RestController
public class UserRestController {

    /*
    * CRUD user service
    * */
    @Autowired
    UserService userService;

    /*
    * user role service
    * */
    @Autowired
    UserRoleService userRoleService;

    @RequestMapping(value = {"/rest/get/"}, method = RequestMethod.GET)
    public Callable<ResponseEntity<String>> get(String role) {
        return new Callable<ResponseEntity<String>>() {
            @Override
            public ResponseEntity call() throws Exception {
                userService.findAllByRole(role).forEach(v -> System.out.println(v.getToken()));
                return new ResponseEntity(HttpStatus.OK);
            }
        };
    }

    /*
    * save user
    * */
    @RequestMapping(value = {"/rest/user/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<String>> saveUser(@RequestBody DeviceMassage deviceMassage) {
        /* logger */
        System.out.println(deviceMassage.toString());
        return new Callable<ResponseEntity<String>>() {
            @Override
            public ResponseEntity<String> call() throws Exception {
                String userToken = deviceMassage.getCurrentToken();
                String userRole = deviceMassage.getRole();

                if (deviceMassage.getRole() != null && !userService.exist(userToken)) {

                    UserEntity entity = new UserEntity();

                    entity.setToken(userToken);

                    try {
                        entity.setUser_role(userRoleService.findRoleByName(userRole));
                    } catch (NullPointerException e) {
                        /*logger */
                        System.out.println("Incorrect user role: " + userRole);
                        return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
                    }
                    entity.setEnable(true);
                    entity.setCurrentLatitude(deviceMassage.getCurrentLat());
                    entity.setCurrentLongitude(deviceMassage.getCurrentLon());
                    entity.setDestinationLatitude(deviceMassage.getDestinationLat());
                    entity.setDestinationLongitude(deviceMassage.getDestinationLon());

                    userService.save(entity);
                    return new ResponseEntity<String>("", HttpStatus.CREATED);
                } else {
                    /* logger */
                    System.out.println("Conflict -----------------------------------------------");
                    return new ResponseEntity<String>("", HttpStatus.CONFLICT);
                }
            }
        };
    }

    /*
    * update user
    * */
    @RequestMapping(value = {"/rest/user/"}, method = RequestMethod.PUT)
    public Callable<ResponseEntity<UserEntity>> updateUser(@RequestBody DeviceMassage deviceMassage) {
        /*logger */
        System.out.println(deviceMassage.toString());
        return () -> {
            try {
                /* update only role*/
                String currentRole = userService.findUserByToken(deviceMassage.getCurrentToken())
                        .getUser_role()
                        .getUser_role();
                if (currentRole != null && !Objects.equals(currentRole, deviceMassage.getRole())) {

                    UserEntity userEntity = userService.findUserByToken(deviceMassage.getCurrentToken());
                    userEntity.setUser_role(userRoleService.findRoleByName(deviceMassage.getRole()));
                    userService.update(userEntity);
                }
                /* update only token */
                String oldToken = deviceMassage.getOldToken();
                UserEntity userEntity = userService.findUserByToken(oldToken);

                userEntity.setToken(deviceMassage.getCurrentToken());

                userService.update(userEntity);
                return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
            } catch (NullPointerException e) {
                /*logger */
                return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
            }
        };
    }

    /*
    * delete user
    * */
    @RequestMapping(value = {"/rest/user/{token}"}, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<UserEntity>> deleteUser(@PathVariable("token") String token) {
        /*logger*/
        System.out.println(token);
        return () -> {
            UserEntity entity = userService.findUserByToken(token);
            if (entity == null) {
                /*logger*/
                System.out.println("User with token " + token + " not found");
                return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
            }
            userService.deleteByToken(token);
            return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
        };
    }

}
