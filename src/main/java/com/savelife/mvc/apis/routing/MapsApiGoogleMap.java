package com.savelife.mvc.apis.routing;


import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import com.savelife.mvc.model.routing.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Component
public class MapsApiGoogleMap implements MapsApi {

    @Autowired
    private GeoApiContext geoApiContext;

    public MapsApiGoogleMap() {
    }

    public void setGeoApiContext(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public GeoApiContext getGeoApiContext() {
        return geoApiContext;
    }

    @Override
    public List<NodeEntity> getRoute(Double startLat, Double startLng, Double endLat, Double endLng) {
        if (Objects.nonNull(startLat)
                && Objects.nonNull(startLng)
                && Objects.nonNull(endLat)
                && Objects.nonNull(endLng)) {
            try {
                DirectionsResult result = DirectionsApi.newRequest(geoApiContext).mode(TravelMode.DRIVING)
                        .origin(new LatLng(startLat, startLng))
                        .destination(new LatLng(endLat, endLng)).await();

                return parseResult(result);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public List<NodeEntity> parseResult(DirectionsResult directionsResult) {

        if (directionsResult == null)
            return null;

        List<NodeEntity> nodesList = new ArrayList<>();
        DirectionsRoute[] routes = directionsResult.routes;
        DirectionsStep lastStep = null;

        for (DirectionsRoute route : routes) {
            DirectionsLeg[] legs = route.legs;

            for (DirectionsLeg leg : legs) {
                DirectionsStep[] steps = leg.steps;

                for (DirectionsStep step : steps) {
                    lastStep = step;
                    nodesList.add(new NodeEntity(step.startLocation.lat, step.startLocation.lng));
                    System.out.println("lng : " + step.startLocation.lng + "     lat : " + step.startLocation.lat);
                }
            }
        }

        nodesList.add(new NodeEntity(lastStep.endLocation.lat, lastStep.endLocation.lng));
        return nodesList;
    }


}