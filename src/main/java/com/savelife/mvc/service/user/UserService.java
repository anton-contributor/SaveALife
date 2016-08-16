package com.savelife.mvc.service.user;

import com.savelife.mvc.model.user.UserEntity;

import java.util.List;

/**
 * Created by anton on 16.08.16.
 */
public interface UserService {

    UserEntity findUserByToken(String token);

    UserEntity findUserById(Long id_user);

    List<UserEntity> findAllUsers();

    boolean exist(String token);

    void save(UserEntity userEntity);

    void delete(UserEntity entity);
}
