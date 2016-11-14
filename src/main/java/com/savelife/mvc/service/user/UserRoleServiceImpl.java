package com.savelife.mvc.service.user;

import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by anton on 19.08.16.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleRepository userRoleRepository;


    @Override
    public UserRoleEntity findRoleById(Integer id) {
        return userRoleRepository.findOne(id);
    }

    @Override
    public UserRoleEntity findRoleByName(String nameRole) {
        return userRoleRepository.findByUserRole(nameRole);
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return (List)userRoleRepository.findAll();
    }


}
