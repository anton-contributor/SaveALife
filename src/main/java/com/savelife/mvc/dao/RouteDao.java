package com.savelife.mvc.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anton on 27.07.16.
 */
public interface RouteDao<T, ID extends Serializable> {

    T findRouteById(ID id);

    List<T> findAll();

    void save(T entity);
    void delete(T entity);
}
