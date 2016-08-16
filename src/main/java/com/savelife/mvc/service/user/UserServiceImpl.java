package com.savelife.mvc.service.user;

import com.savelife.mvc.dao.user.UserDao;
import com.savelife.mvc.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anton on 16.08.16.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao dao;

    @Override
    public UserEntity findUserByToken(String token) {
        return dao.findUserByToken(token);
    }

    @Override
    public UserEntity findUserById(Long id_user) {
        return dao.findUserById(id_user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public boolean exist(String token) {
        return dao.findUserByToken(token)!= null;
    }

    @Override
    public void save(UserEntity userEntity) {
        dao.save(userEntity);
    }

    @Override
    public void delete(UserEntity entity) {
            dao.delete(entity);
    }
}
