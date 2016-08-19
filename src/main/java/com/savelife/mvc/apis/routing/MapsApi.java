package com.savelife.mvc.apis.routing;

import com.savelife.mvc.model.routing.NodeEntity;

import java.util.List;

/**
 * Created by anton on 19.08.16.
 */
public interface MapsApi {
    List<NodeEntity> getRoute(Double startLat, Double startLng, Double endLat, Double endLng);
}
