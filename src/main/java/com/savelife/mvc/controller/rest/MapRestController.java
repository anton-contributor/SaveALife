package com.savelife.mvc.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savelife.mvc.model.massaging.Data;
import com.savelife.mvc.model.massaging.Massage;
import com.savelife.mvc.model.massaging.device.Device;
import com.savelife.mvc.service.device.DeviceService;
import com.savelife.mvc.service.routing.RouteService;
import com.savelife.mvc.service.sender.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Callable;

import static com.savelife.mvc.model.storing.DevicesContainer.getInstance;

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
    DeviceService deviceService;

    @RequestMapping(value = {"/rest/"}, method = RequestMethod.GET)
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
    }

    /*
    * processing a post request from the device with its unique token
     * to add it to device map
    * */
    @RequestMapping(value = {"/rest/connect/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> save(String token){

        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {

                    getInstance().put(token,new Device(token));


                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
        };
    }

    /*
    * processing put request from the device to update its token(to clean connect with it)
    * */
    @RequestMapping(value = {"/rest/connect/"}, method = RequestMethod.PUT)
    public Callable<ResponseEntity<Void>> update(String token){
        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {
                if (deviceService.exist(token)){

                }
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        };
    }

}
