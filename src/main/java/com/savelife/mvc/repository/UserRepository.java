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

    UserEntity findByToken(String token);

    @Transactional
    void deleteByToken(String token);

    List<UserEntity> findAllByUserRole(UserRoleEntity userRoleEntity);

    @Modifying
    @Transactional
    @Query(value = "update savelife.user set token = ?1, userRoleID = ?2, currentLatitude = ?3, currentLongitude = ?4, enable = ?5 where idUser = ?6", nativeQuery = true)
    void update(String token, Integer userRoleID, Double currnetLatitude, Double currentLongitude, boolean enable, Long idUser);
}
