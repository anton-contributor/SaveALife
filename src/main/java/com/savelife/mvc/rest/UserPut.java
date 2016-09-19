package com.savelife.mvc.rest;

import com.savelife.mvc.model.messaging.device.DeviceMessage;
import com.savelife.mvc.model.user.UserEntity;
import com.savelife.mvc.service.user.UserRoleService;
import com.savelife.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * user rest controller receiving put requests
 */
@RestController
@RequestMapping(value = {"/rest/user"})
public class UserPut {

    private static Logger logger = Logger.getLogger(UserPut.class.getName());

    /*
    * CRUD user service
    * */
    @Autowired
    UserService userService;

    /*
    * user role service
    * */
    @Autowired
    UserRoleService userRoleService;

    /*
    * update user
    * */
    @PutMapping()
    public Callable<ResponseEntity<UserEntity>> updateUser(@RequestBody DeviceMessage deviceMessage) {
        logger.info("Received " + deviceMessage);
        return () -> {
            try {
                /* update only role*/
                String currentRole = userService.findUserByToken(deviceMessage.getCurrentToken())
                        .getUserRole()
                        .getUserRole();
                logger.info("Updating role " + currentRole);
                if (Objects.nonNull(currentRole) && !Objects.equals(currentRole, deviceMessage.getRole())) {
                    logger.info("Updating role " + currentRole + " to " + deviceMessage.getRole());
                    UserEntity userEntity = userService.findUserByToken(deviceMessage.getCurrentToken());
                    userEntity.setUserRole(userRoleService.findRoleByName(deviceMessage.getRole()));
                    userService.update(userEntity);
                    logger.info("Updated " + userEntity);
                }
                /* update only token */
                String oldToken = deviceMessage.getOldToken();
                logger.info("Updating token " + oldToken + " to " + deviceMessage.getCurrentToken());
                UserEntity userEntity = userService.findUserByToken(oldToken);

                userEntity.setToken(deviceMessage.getCurrentToken());

                userService.update(userEntity);
                logger.info("Updated " + userEntity);
                return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
            } catch (NullPointerException e) {
                logger.info("Exception " + e);
                return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
            }
        };
    }
}
