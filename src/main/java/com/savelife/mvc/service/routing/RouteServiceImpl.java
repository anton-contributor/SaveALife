package com.savelife.mvc.service.routing;

/**
 * Created by anton on 27.07.16.
 */

import com.savelife.mvc.dao.route.RouteDao;
import com.savelife.mvc.model.routing.RouteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("routeService")
@Transactional
public class RouteServiceImpl implements RouteService<RouteEntity,Long>{


    @Autowired
    private RouteDao routeDao;

    @Override
    public RouteEntity findById(Long aLong) {
        return (RouteEntity) routeDao.findRouteById(aLong);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RouteEntity> findAll() {
        List<RouteEntity> entities = routeDao.findAll();
        return entities;
    }

    @Override
    public void save(RouteEntity entity) {
        routeDao.save(entity);
    }

    @Override
    public void delete(Long aLong) {
        routeDao.delete(findById(aLong));
    }
}
