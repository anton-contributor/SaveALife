package com.savelife.mvc.dao.user;

import com.savelife.mvc.model.user.UserRoleEntity;

import java.util.List;

/**
 * Created by anton on 16.08.16.
 */
public interface UserRoleDao {

    UserRoleEntity findRoleByName(String name);

    UserRoleEntity findRoleById(Integer id_user_role);

    List<UserRoleEntity> findAll();


    void save(UserRoleEntity entity);

    void deleteRole(UserRoleEntity entity);
}
