package com.savelife.mvc.service.routing;

import com.savelife.mvc.apis.routing.MapsApi;
import com.savelife.mvc.model.routing.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anton on 19.08.16.
 */
@Service("routeService")
public class RouteServiceImpl implements RoutingService {

    @Autowired
    private MapsApi mapsApi;

    @Override
    public List<NodeEntity> getRoute(Double startLat, Double startLng, Double endLat, Double endLng) {
        return mapsApi.getRoute(startLat, startLng, endLat, endLng);
    }
}
