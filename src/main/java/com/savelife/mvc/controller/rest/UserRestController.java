package com.savelife.mvc.controller.rest;

import com.savelife.mvc.model.massaging.device.DeviceMassage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*
    * save user
    * */
    @RequestMapping(value = {"/rest/user/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<String>> saveUser(@RequestBody DeviceMassage deviceMassage) {
        return new Callable<ResponseEntity<String>>() {
            @Override
            public ResponseEntity<String> call() throws Exception {
                String userToken = deviceMassage.getCurrentToken();
                if (deviceMassage.getRole() != null & !userService.exist(userToken)) {

                    UserEntity entity = new UserEntity();

                    entity.setToken(userToken);
                    try {
                        entity.setUser_role(userRoleService.findRoleByName(deviceMassage.getRole()));
                    } catch (NullPointerException e) {
                        System.out.println("Incorrect user role: " + deviceMassage.getRole());
                        return new ResponseEntity<String>("", HttpStatus.CONFLICT);
                    }

                    entity.setCurrentLatitude(String.valueOf(deviceMassage.getCurrentLat()));
                    entity.setCurrentLongitude(String.valueOf(deviceMassage.getCurrentLon()));

                    userService.save(entity);
                    return new ResponseEntity<String>("", HttpStatus.CREATED);
                } else {
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
        return () -> {
            try {
                String oldToken = deviceMassage.getOldToken();
                UserEntity userEntity = userService.findUserByToken(oldToken);

                userEntity.setToken(deviceMassage.getCurrentToken());
                userEntity.setUser_role(userRoleService.findRoleByName(deviceMassage.getRole()));

                userService.update(userEntity);
                return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);

            } catch (NullPointerException e) {
                return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
            }
        };
    }

    /*
    * delete user
    * */
    @RequestMapping(value = {"/rest/user/{token}"}, method = RequestMethod.DELETE)
    public Callable<ResponseEntity<UserEntity>> deleteUser(@PathVariable("token") String token) {
        return () -> {
            UserEntity entity = userService.findUserByToken(token);
            if (entity == null) {
                System.out.println("User with token " + token + " not found");
                return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
            }
            userService.deleteByToken(token);
            return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
        };
    }

}
