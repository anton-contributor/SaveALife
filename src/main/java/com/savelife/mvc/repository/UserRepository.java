package com.savelife.mvc.repository;

import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.model.user.UserRoleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gleb-pc on 8/22/16.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    void deleteByToken(String token);

    UserEntity findByToken(String token);

    UserEntity findByEmail(String email);

    List<UserEntity> findAllByUserRole(UserRoleEntity userRoleEntity);

    List<UserEntity> findByTokenNot(String token);

    UserEntity findByPhoneNumber(String phoneNumber);
}
