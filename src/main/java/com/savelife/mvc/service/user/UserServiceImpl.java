package com.savelife.mvc.service.user;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.repository.UserRepository;
import com.savelife.mvc.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

/**
 * Created by anton on 16.08.16.
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public UserEntity findUserByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findUserById(long idUser) {
        return userRepository.findOne(idUser);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAllUsers() {
        return (List) userRepository.findAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAllByRole(String role) {
        UserRoleEntity userRole = userRoleRepository.findByUserRole(role);
        return userRepository.findAllByUserRole(userRole);
    }

    /* hard code */
    @Override
    public List<UserEntity> findAllUnableDrivers() {
        return findAllByRole("Driver")
                .stream()
                .filter(v -> !v.isEnable())
                .collect(toList());
    }

    @Override
    public List<UserEntity> findAllBeyondCurrent(String token) {
        return userRepository.findByTokenNot(token);
    }

    @Override
    public UserEntity findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }


    @Override
    public boolean exist(String token) {
        logger.info("Inside of the exist method");
        return Objects.nonNull(userRepository.findByToken(token));
    }

    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void delete(UserEntity entity) {
        userRepository.delete(entity);
    }

    @Override
    public void deleteByToken(String token) {
        userRepository.deleteByToken(token);
    }

    @Override
    public void setAllUsersUnable() {
        userRepository
                .findAll()
                .forEach(v -> {
                    v.setEnable(false);
                    save(v);
                });
    }

    @Override
    public void setAllUsersEnable() {
        userRepository
                .findAll()
                .forEach(v -> {
                    v.setEnable(true);
                    save(v);
                });
    }


    @Override
    @Transactional
    public void addContactById(Long userId, Long contactId) {
        UserEntity user = userRepository.findOne(userId);
        UserEntity contact = userRepository.findOne(contactId);
        user.getUserContacts().add(contact);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addContactByPhoneNumber(String userPhoneNumber, String contactPhoneNumber) {
        UserEntity user = userRepository.findByPhoneNumber(userPhoneNumber);
        UserEntity contact = userRepository.findByPhoneNumber(contactPhoneNumber);
        user.getUserContacts().add(contact);
        userRepository.save(user);
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        UserEntity userEntity = userRepository.findByPhoneNumber(phoneNumber);
        if (userEntity == null)
            return false;
        return true;
    }

//    @Override
//    @Transactional
//    public boolean deleteContact(String userNumber, String contactNumber) {
//
//        UserEntity user = userRepository.findByPhoneNumber(userNumber);
//        UserEntity contact = userRepository.findByPhoneNumber(contactNumber);
//
//        user.getUserContacts().remove(contact);
//        return true;
//    }

    @Override
    @Transactional
    public boolean deleteContact(String contactNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserPhoneNumber = auth.getName();

        UserEntity user = userRepository.findByPhoneNumber(currentUserPhoneNumber);
        UserEntity contact = userRepository.findByPhoneNumber(contactNumber);

        if(user == null || contact == null)
            return false;

        List<UserEntity> userContacts = user.getUserContacts();

        if(userContacts.contains(contact))
            userContacts.remove(contact);
        else
            return false;

        return true;
    }
}