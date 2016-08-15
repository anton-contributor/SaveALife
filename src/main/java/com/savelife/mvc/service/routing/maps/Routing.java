package com.savelife.mvc.service.routing.maps;

import java.util.List;

public interface Routing<T> {

    T getRouteEntity();
    List<T> getAllRouteEntity();

}
