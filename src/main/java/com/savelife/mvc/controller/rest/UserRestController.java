package com.savelife.mvc.controller.rest;

import com.savelife.mvc.model.massaging.device.DeviceMassage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Callable;

import static com.savelife.mvc.model.user.singleton.UserRoleContainer.getRole;

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
            System.out.println(userService.findUserByToken("token"));
            return new ResponseEntity<String>("asynchrone GET response ", HttpStatus.OK);
        };
    }

    /*
    * processing a post request from the device with its unique token
     * to add it to device map
    * */
    @RequestMapping(value = {"/rest/registration/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> saveDevice(@RequestBody DeviceMassage serverMassage) {
        return () -> {
            if (serverMassage.getRole() != null & serverMassage.getCurrentToken() != null) {

                UserEntity entity = new UserEntity();
                entity.setToken(serverMassage.getCurrentToken());
                entity.setUserRoleIdUserRole(getRole(serverMassage.getRole()));

                userService.save(entity);
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
        };
    }

    /*
    * processing put request from the device to update its token(to clean connect with it)
    * */
    @RequestMapping(value = {"/rest/registration/"}, method = RequestMethod.PUT)
    public Callable<ResponseEntity<Void>> updateDevice(@RequestBody DeviceMassage serverMassage) {
        return () -> {
            if (userService.exist(serverMassage.getCurrentToken())) {

                UserEntity userEntity = new UserEntity();
                userEntity.setToken(serverMassage.getCurrentToken());
                userEntity.setUserRoleIdUserRole(getRole(serverMassage.getRole()));

                userService.update(userEntity);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        };
    }

}
