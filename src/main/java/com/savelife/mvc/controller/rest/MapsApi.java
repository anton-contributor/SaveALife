package com.savelife.mvc.controller.rest;

import com.savelife.mvc.model.routing.NodeEntity;

import java.util.List;

/**
 * Created by gleb-pc on 8/17/16.
 */
public interface MapsApi {
    public List<NodeEntity> getRoute(Double startLat, Double startLng, Double endLat, Double endLng);
}
