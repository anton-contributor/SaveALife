package com.savelife.mvc.service.user;

import com.savelife.mvc.dao.user.UserRoleDao;
import com.savelife.mvc.model.user.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anton on 19.08.16.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao dao;

    @Override
    public UserRoleEntity findRoleById(Integer id_role) {
        return dao.findRoleById(id_role);
    }

    @Override
    public UserRoleEntity findRoleByName(String name_role) {
        return null;
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return dao.findAll();
    }
}
