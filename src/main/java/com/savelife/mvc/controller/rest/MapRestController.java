package com.savelife.mvc.controller.rest;

import com.savelife.mvc.model.RouteEntity;
import com.savelife.mvc.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapRestController {

    @Autowired
    RouteService routeService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ResponseEntity<String> print(){

        RouteEntity entity = new RouteEntity();

        routeService.save(entity);
        return new ResponseEntity<String>(routeService.findById(new Long(1)).toString(), HttpStatus.OK);
    }
}
