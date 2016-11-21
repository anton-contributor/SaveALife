package com.savelife.mvc.service.user;

import com.savelife.mvc.model.user.UserEntity;
import org.springframework.transaction.annotation.Transactional;

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

    List<UserEntity> findAllBeyondCurrent(String token);

    List<UserEntity> findAllUnableDrivers();

    UserEntity findByPhoneNumber(String phoneNumber);


    boolean exist(String token);

    void save(UserEntity userEntity);

    void delete(UserEntity entity);

    void deleteByToken(String token);

    void setAllUsersUnable();

    void setAllUsersEnable();



    void addContactById(Long userId, Long contactId);

    void addContactByPhoneNumber(String userPhoneNumber, String contactPhoneNumber);

    boolean existByPhoneNumber(String phoneNumber);

    boolean deleteContact(String ContactNumber);

    //    boolean deleteContact(String UserNumber, String ContactNumber);

}
