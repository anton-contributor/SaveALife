package com.savelife.mvc.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anton on 27.07.16.
 */
public interface RouteService<T, ID extends Serializable> {

    T findById(ID id_route);

    List<T> findAll();

    void save(T entity);
    void delete(ID id);
}
