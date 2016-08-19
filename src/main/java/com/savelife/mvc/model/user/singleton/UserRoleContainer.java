package com.savelife.mvc.model.user.singleton;


import com.savelife.mvc.model.user.UserRoleEntity;
import com.savelife.mvc.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRoleContainer {

    @Autowired
    private UserRoleService userRoleService;

    private Map<String, Integer> roles;

    public static class UserRoleContainerHolder {

        public static final UserRoleContainer HOLDER_INSTANCE = new UserRoleContainer();

    }

    public static Integer getRole(String key) {
        return UserRoleContainerHolder.HOLDER_INSTANCE.roles.get(key);
    }

    private UserRoleContainer() {
        this.roles = new ConcurrentHashMap<>();
        for (UserRoleEntity c : userRoleService.findAll()) {
            roles.put(c.getUserRole(),c.getIdUserRole());
        }
    }
}
