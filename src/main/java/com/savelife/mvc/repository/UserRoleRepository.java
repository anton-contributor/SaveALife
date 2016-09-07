package com.savelife.mvc.repository;


import com.savelife.mvc.model.user.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gleb-pc on 8/23/16.
 */
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Integer> {
    UserRoleEntity findByUserRole(String userRole);
}
