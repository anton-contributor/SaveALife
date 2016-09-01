package com.savelife.mvc.dao.user;

import com.savelife.mvc.dao.AbstractDao;
import com.savelife.mvc.model.user.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anton on 16.08.16.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, UserEntity> implements UserDao {

    @Override
    public UserEntity findUserByToken(String token) {

        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("token", token));
        return (UserEntity) c.uniqueResult();
    }

    @Override
    public UserEntity findUserById(long id_user) {
        UserEntity userEntity = getByKey(id_user);
        return userEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAllUsers() {
        return createEntityCriteria().list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> findAllEnable() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("enable", true));//fro all enable drivers
        return (List<UserEntity>) criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> findAllUnable() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("enable", false));//fro all unable drivers
        return (List<UserEntity>) criteria.list();
    }

    @Override
    public void save(UserEntity userEntity) {
        persist(userEntity);
    }

    @Override
    public void delete(UserEntity entity) {
        delete(entity);
    }


    @Override
    public void deleteByToken(String token) {
        Query query = getSession().createSQLQuery("delete from user WHERE token = :token");
        query.setString("token", token);
        query.executeUpdate();
    }
}
