package com.savelife.mvc.controller.rest;

import com.savelife.mvc.model.massaging.device.DeviceMassage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.routing.RoutingService;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
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
    * route service
    * */
    @Autowired
    RoutingService routingService;

    /*
    * user role service
    * */
    @Autowired
    UserRoleService userRoleService;

    @RequestMapping(value = {"/rest/"}, method = RequestMethod.GET)
    public Callable<ResponseEntity<String>> print() throws UnsupportedEncodingException, FileNotFoundException {
        /*System.out.println("Inside the get request");

        ServerMassage m = new ServerMassage();
        m.setCurrentToken("ebd9TtNVVIY:APA91bGyS26_wHJc6ZquBArgYCdPTeSaVlyodCkBrseUxKGVxMnTjG0OZt9t-W_7abe7t4a_NZFF-67tKIEsgyaPW7w9F8Jy9MzLu05SRHahizVRdRYUQnKs4a4IMlp9zKhok68xsn4L");
        Data d = new Data();
        d.setMassage("Hi, would you like to rebuild your road path?");
        m.setData(d);

        ObjectMapper mapper = new ObjectMapper();
        try {
            senderService.echo(mapper.writeValueAsString(m));
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

        System.out.println("After request");*/

        return () -> {

            System.out.println(userService.findUserById(1).getToken());
            return new ResponseEntity<String>("asynchrone GET response ", HttpStatus.OK);
        };
    }

    /*
    * save user
    * */
    @RequestMapping(value = {"/rest/user/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> saveUser(@RequestBody DeviceMassage deviceMassage) {
        return () -> {
            String userToken = deviceMassage.getCurrentToken();
            if (deviceMassage.getRole() != null & !userService.exist(userToken)) {

                UserEntity entity = new UserEntity();

                entity.setToken(userToken);
                try {
                    entity.setUser_role(userRoleService.findRoleByName(deviceMassage.getRole()));
                } catch (NullPointerException e) {
                    System.out.println("Incorrect user role: " + deviceMassage.getRole());
                    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }

//                entity.setUserRoleIdUserRole(getRole(deviceMassage.getRole()));
                entity.setLatitude(String.valueOf(deviceMassage.getCurrentLat()));
                entity.setLongitude(String.valueOf(deviceMassage.getCurrentLon()));

                userService.save(entity);
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } else {
                System.out.println("Conflict -----------------------------------------------");
                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
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
