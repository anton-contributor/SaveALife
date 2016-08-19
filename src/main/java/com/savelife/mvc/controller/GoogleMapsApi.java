package com.savelife.mvc.controller;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import com.savelife.mvc.configuration.ApplicationConfiguration;
import com.savelife.mvc.configuration.GoogleMapsApiConfiguration;
import com.savelife.mvc.controller.rest.MapsApi;
import com.savelife.mvc.model.routing.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gleb-pc on 8/17/16.
 */
public class GoogleMapsApi implements MapsApi{

    @Autowired
    private GeoApiContext geoApiContext;

    public GoogleMapsApi() {
    }

    public void setGeoApiContext(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public GeoApiContext getGeoApiContext() {
        return geoApiContext;
    }

    @Override
    public List<NodeEntity> getRoute(Double startLat, Double startLng, Double endLat, Double endLng){
        DirectionsResult result = null;
        LatLng origin = new LatLng(startLat, startLng);
        LatLng destination = new LatLng(endLat, endLng);
        try {
            result = DirectionsApi.newRequest(geoApiContext).mode(TravelMode.DRIVING)
                    .origin(origin)
                    .destination(destination).await();

            return parseResult(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NodeEntity> parseResult(DirectionsResult directionsResult){

        if(directionsResult == null)
            return  null;

        List<NodeEntity> nodesList = new ArrayList<>();
        DirectionsRoute[] routes = directionsResult.routes;
        DirectionsStep lastStep = null;

        for (DirectionsRoute route : routes) {
            DirectionsLeg[] legs = route.legs;

            for(DirectionsLeg leg : legs){
                DirectionsStep[] steps = leg.steps;

                for(DirectionsStep step : steps){
                    lastStep = step;
                    nodesList.add(new NodeEntity(step.startLocation.lat, step.startLocation.lng));
                    System.out.println("lng : " + step.startLocation.lng + "     lat : " + step.startLocation.lat);
                }
            }
        }

        nodesList.add(new NodeEntity(lastStep.endLocation.lat, lastStep.endLocation.lng));
        return  nodesList;
    }


}
