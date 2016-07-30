package com.savelife.mvc.controller.rest;

import com.savelife.mvc.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;


@RestController
public class MapRestController {

    @Autowired
    RouteService routeService;

    @RequestMapping(value = {"/rest/"}, method = RequestMethod.GET)
    public Callable<ResponseEntity<String>> print(){
        return new Callable<ResponseEntity<String>>() {
            @Override
            public ResponseEntity<String> call() throws Exception {
                return new ResponseEntity<String>("asynchrone GET response ", HttpStatus.OK);
            }
        };
    }

    @RequestMapping(value = {"/rest/"}, method = RequestMethod.POST)
    public Callable<ResponseEntity<Void>> postRoute(String s){
        System.out.println("Inside of the rest post method");
        System.out.println(s);
        return new Callable<ResponseEntity<Void>>() {
            @Override
            public ResponseEntity<Void> call() throws Exception {
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }
        };
    }

}
