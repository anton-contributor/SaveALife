package com.savelife.mvc.model.user.singleton;


import com.savelife.mvc.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRoleContainer {

    @Autowired
    private UserRoleService userRoleService;

    private Map<String, Integer> roles;

    public UserRoleContainer() {
        /*this.roles = new ConcurrentHashMap<>();
        List<UserRoleEntity> list = userRoleService.findAll();
        list.forEach((k) -> {
            roles.put(k.getUser_role(), k.getId_user_role());
        });*/
    }

    public static class UserRoleContainerHolder {

        public static final UserRoleContainer HOLDER_INSTANCE = new UserRoleContainer();

    }

    public static Integer getRole(String key) {
        return UserRoleContainerHolder.HOLDER_INSTANCE.roles.get(key);
    }

}
