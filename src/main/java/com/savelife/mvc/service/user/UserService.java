package com.savelife.mvc.service.user;

import com.savelife.mvc.model.user.UserEntity;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by anton on 16.08.16.
 */
public interface UserService {

    UserEntity findUserByToken(String token);

    UserEntity findUserById(long id_user);

    UserEntity findUserByEmail(String email);

    List<UserEntity> findAllUsers();

    List<UserEntity> findAllByRole(String role);

    boolean exist(String token);

    void save(UserEntity userEntity);

    void delete(UserEntity entity);

    void deleteByToken(String token);

    void setAllUsersUnable();

    void setAllUsersEnable();

    List<UserEntity> findAllBeyondCurrent(String token);

    List<UserEntity> findAllUnableDrivers();
}
