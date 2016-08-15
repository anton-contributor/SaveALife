package com.savelife.mvc.dao.route;

import com.savelife.mvc.dao.AbstractDao;
import com.savelife.mvc.model.routing.RouteEntity;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anton on 27.07.16.
 */
@Repository("routeDao")
public class RouteDaoIml extends AbstractDao<Long,RouteEntity> implements RouteDao<RouteEntity,Long> {


    @Override
    public RouteEntity findRouteById(Long aLong) {
        return getByKey(aLong);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RouteEntity> findAll() {
        Criteria criteria = createEntityCriteria();
        return criteria.list();
    }

    @Override
    public void save(RouteEntity entity) {
        persist(entity);
    }

    @Override
    public void delete(RouteEntity entity){
        delete(entity);
    }

}
