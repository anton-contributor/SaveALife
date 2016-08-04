package com.savelife.mvc.service;

import com.savelife.mvc.model.NodeEntity;
import com.savelife.mvc.service.exceptions.IncorrectRequstCoordinatesException;
import com.savelife.mvc.service.exceptions.RoutingServerNotRespondingException;
import com.savelife.mvc.service.maps.Routing;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anton on 27.07.16.
 */
public interface RouteService<T, ID extends Serializable> {

    Routing getRouting(NodeEntity start, NodeEntity end) throws IncorrectRequstCoordinatesException, RoutingServerNotRespondingException;

    T findById(ID id_route);
    List<T> findAll();
    void save(T entity);
    void delete(ID id);
}
