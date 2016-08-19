package com.savelife.mvc.dao.user;

import com.savelife.mvc.dao.AbstractDao;
import com.savelife.mvc.model.user.UserRoleEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anton on 16.08.16.
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<Integer,UserRoleEntity> implements UserRoleDao{

    @Override
    public UserRoleEntity findRoleByName(String name) {
        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("role",name));
        return (UserRoleEntity) c.uniqueResult();
    }

    @Override
    public UserRoleEntity findRoleById(Integer id_user_role) {
        return getByKey(id_user_role);
    }

    @SuppressWarnings("uncheked")
    @Override
    public List<UserRoleEntity> findAll() {
        return createEntityCriteria().list();
    }

    @Override
    public void save(UserRoleEntity entity) {
            persist(entity);
    }

    @Override
    public void deleteRole(UserRoleEntity entity) {
        delete(entity);
    }

}
