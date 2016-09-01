package com.savelife.mvc.service.user;

import com.savelife.mvc.dao.user.UserDao;
import com.savelife.mvc.dao.user.UserRoleDao;
import com.savelife.mvc.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by anton on 16.08.16.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserRoleDao roleDao;

    @Override
    public UserEntity findUserByToken(String token) {
        return dao.findUserByToken(token);
    }

    @Override
    public UserEntity findUserById(long id_user) {
        return dao.findUserById(id_user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return dao.findAllUsers();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAllByRole(String role) {

        return dao.findAllUsers().stream()
                .filter(v -> v.getUser_role().getUser_role().equals(role))
                .collect(toList());
    }

    @Override
    public boolean exist(String token) {
        return dao.findUserByToken(token) != null;
    }

    @Override
    public void save(UserEntity userEntity) {
        dao.save(userEntity);
    }

    @Override
    public void delete(UserEntity entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteByToken(String token) {
        dao.deleteByToken(token);
    }

    @Override
    public void setAllUsersUnable() {
        dao.findAllEnable().forEach((v) -> {
            v.setEnable(false);
            update(v);
        });
    }

    @Override
    public void setAllUsersEnable() {
        dao.findAllUnable().forEach((v) -> {
            v.setEnable(true);
            update(v);
        });
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     * */
    @Override
    public void update(UserEntity entity) {
        UserEntity user = dao.findUserById(entity.getIdUser());
        if (user != null) {
            user.setToken(entity.getToken());
            user.setUser_role(entity.getUser_role());
            user.setCurrentLatitude(entity.getCurrentLatitude());
            user.setCurrentLongitude(entity.getCurrentLongitude());
            user.setEnable(entity.isEnable());
        } else {
            System.out.println("Not_FOUND");
        }
    }
}
