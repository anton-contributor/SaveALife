package com.savelife.mvc.dao.user;

import com.savelife.mvc.dao.AbstractDao;
import com.savelife.mvc.model.user.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anton on 16.08.16.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, UserEntity> implements UserDao{

    @Override
    public UserEntity findUserByToken(String token) {

        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("token",token));
        return (UserEntity) c.uniqueResult();
    }

    @Override
    public UserEntity findUserById(Long id_user) {
        return getByKey(id_user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAllUsers() {
        return createEntityCriteria().list();
    }

    @Override
    public void save(UserEntity userEntity) {
        persist(userEntity);
    }

    @Override
    public void delete(UserEntity entity) {
        delete(entity);
    }
}
