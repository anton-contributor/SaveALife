package com.savelife.mvc.dao.user;

import com.savelife.mvc.model.user.UserEntity;

import java.util.List;

/**
 * Created by anton on 16.08.16.
 */
public interface UserDao {

    UserEntity findUserByToken(String token);

    UserEntity findUserById(long id_user);

    List<UserEntity> findAllUsers();

    List<UserEntity> findAllEnable();

    List<UserEntity> findAllUnable();

    void save(UserEntity userEntity);

    void delete(UserEntity entity);

    void deleteByToken(String token);

}
