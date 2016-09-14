package com.savelife.mvc.service.user;

import com.savelife.mvc.model.user.UserEntity;

import java.util.List;

/**
 * Created by anton on 16.08.16.
 */
public interface UserService {

    UserEntity findUserByToken(String token);

    UserEntity findUserById(long id_user);

    List<UserEntity> findAllUsers();

    List<UserEntity> findAllByRole(String role);

    void update(UserEntity entity);

    boolean exist(String token);

    void save(UserEntity userEntity);

    void delete(UserEntity entity);

    void deleteByToken(String token);

    void setAllUsersUnable();

    void setAllUsersEnable();
}
