package com.savelife.mvc.service.user;

import com.savelife.mvc.model.user.UserRoleEntity;

import java.util.List;

/**
 * Created by anton on 19.08.16.
 */
public interface UserRoleService {

    UserRoleEntity findRoleById(Integer id_role);

    UserRoleEntity findRoleByName(String name_role);

    List<UserRoleEntity> findAll();
}
