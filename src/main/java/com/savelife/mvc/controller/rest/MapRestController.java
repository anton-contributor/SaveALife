package com.savelife.mvc.controller.rest;

import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.routing.RouteService;
import com.savelife.mvc.service.sender.SenderService;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class MapRestController {

    /*
    * fro saving route entity
    * */
    @Autowired
    RouteService routeService;

    /*
    * send to android
    * */
    @Autowired
    SenderService senderService;

    /*
    * interacting database device service
    * */
    @Autowired
    UserService userService;

    /*@RequestMapping(value = {"/rest/"}, method = RequestMethod.GET)
    public ResponseEntity<String> print() throws UnsupportedEncodingException, FileNotFoundException {
        System.out.println("Inside the get request");

        Massage m = new Massage();
        m.setTo("ebd9TtNVVIY:APA91bGyS26_wHJc6ZquBArgYCdPTeSaVlyodCkBrseUxKGVxMnTjG0OZt9t-W_7abe7t4a_NZFF-67tKIEsgyaPW7w9F8Jy9MzLu05SRHahizVRdRYUQnKs4a4IMlp9zKhok68xsn4L");
        Data d = new Data();
        d.setMassage("Hi, would you like to rebuild the road path?");
        m.setData(d);

        ObjectMapper mapper = new ObjectMapper();
        try {
            senderService.echo(mapper.writeValueAsString(m));
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

        System.out.println("After request");

        return new ResponseEntity<String>("asynchrone GET response ", HttpStatus.OK);
    }*/

    @RequestMapping(value = {"/rest/get/"}, method = RequestMethod.GET)
    public ResponseEntity<String> get() {

        UserEntity entity = new UserEntity();
        entity.setToken("this is token");
        entity.setUserRoleIdUserRole(1);
        userService.save(entity);

        return new ResponseEntity<String>("asynchrone GET response ", HttpStatus.OK);
    }

    /*
    * processing a post request from the device with its unique token
     * to add it to device map
    * */
    @RequestMapping(value = {"/rest/connect/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> save(String token) {

        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {


                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
        };
    }

    /*
    * processing put request from the device to update its token(to clean connect with it)
    * */
    @RequestMapping(value = {"/rest/connect/"}, method = RequestMethod.PUT)
    public Callable<ResponseEntity<Void>> update(String token) {
        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {
                if (userService.exist(token)) {

                }
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        };
    }

}
