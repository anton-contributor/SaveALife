package com.savelife.mvc.model.user.singleton;


import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRoleContainer {

    @Autowired
    private static UserRoleService userRoleService;

    private static Map<String, Integer> roles;

    static {
        roles = new ConcurrentHashMap<>();
        List<UserRoleEntity> entities = userRoleService.findAll();
        entities.forEach((k)->roles.put(k.getUser_role(),k.getId_user_role()));
    }

    public static Integer getRole(String key) {
        return roles.get(key);
    }


}
