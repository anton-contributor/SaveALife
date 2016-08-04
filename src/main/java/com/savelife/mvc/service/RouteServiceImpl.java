package com.savelife.mvc.service;

/**
 * Created by anton on 27.07.16.
 */

import com.savelife.mvc.dao.RouteDao;
import com.savelife.mvc.model.NodeEntity;
import com.savelife.mvc.model.RouteEntity;
import com.savelife.mvc.service.exceptions.IncorrectRequstCoordinatesException;
import com.savelife.mvc.service.exceptions.RoutingServerNotRespondingException;
import com.savelife.mvc.service.maps.MapService;
import com.savelife.mvc.service.maps.Routing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("routeService")
@Transactional
public class RouteServiceImpl implements RouteService<RouteEntity,Long>{


    @Autowired
    private RouteDao routeDao;
    @Autowired
    private MapService mapService;

    @Override
    public Routing getRouting(NodeEntity start, NodeEntity end) throws IncorrectRequstCoordinatesException, RoutingServerNotRespondingException {
        return mapService.getRouting(start, end);
    }

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
