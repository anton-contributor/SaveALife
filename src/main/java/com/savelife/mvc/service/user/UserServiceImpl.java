package com.savelife.mvc.service.user;

import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.repository.UserRepository;
import com.savelife.mvc.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     * */
    @Override
    public void update(UserEntity entity) {
        logger.info("called UserServiceIml.update function");

      /*  UserEntity user = userRepository.findByToken(entity.getToken());
        if(entity.getToken() != null)
            user.setToken(entity.getToken());
        if(!Objects.isNull(entity.isEnable()))
            user.setEnable(entity.isEnable());
        if(entity.getCurrentLatitude() != null)
            user.setCurrentLatitude(entity.getCurrentLatitude());
        if(entity.getCurrentLongitude() != null)
            user.setCurrentLongitude(entity.getCurrentLongitude());
        if(entity.getDestinationLatitude() != null)
            user.setDestinationLatitude(entity.getDestinationLatitude());
        if(entity.getDestinationLongitude() != null)
            user.setDestinationLongitude(entity.getDestinationLongitude());
        if(!Objects.isNull(entity.getIdUser()))
            user.setIdUser(entity.getIdUser());
        userRepository.save(user);*/
        userRepository.update(entity.getToken(), entity.getUserRole().getId(),
                entity.getCurrentLatitude(), entity.getCurrentLongitude(), entity.isEnable(), entity.getIdUser());
        logger.info("finished UserServiceIml.update function");
    }

    @Override
    public void setAllUsersUnable() {
        Iterable<UserEntity> all = userRepository.findAll();
        for (UserEntity user : all) {
            user.setEnable(false);
            save(user);
        }
    }

    @Override
    public void setAllUsersEnable() {
        Iterable<UserEntity> all = userRepository.findAll();
        for (UserEntity user : all) {
            user.setEnable(true);
            save(user);
        }
    }

    @Override
    public List<UserEntity> findAllBeyondCurrent(String token) {
       /* List<UserEntity> list = userRepository.findByTokenNot(token);
        list.forEach(v -> logger.info(v.toString()));*/
        List<UserEntity> listResult = new ArrayList<>();
        List<UserEntity> list = (List) userRepository.findAll();
        for (UserEntity e : list) {
            if (!Objects.equals(e.getToken(), token)) {
                listResult.add(e);
            }
        }
        listResult.forEach(v -> System.out.println(v));

        return listResult;
       /* return userRepository.findByTokenNot(token);*/
    }
}
