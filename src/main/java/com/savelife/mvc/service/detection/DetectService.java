package com.savelife.mvc.service.detection;

import com.savelife.mvc.model.user.UserEntity;

import java.util.List;

/**
 * Service to detect appropriate
 */
public interface DetectService<T> {

    List<UserEntity> detect(T radius, T centerX, T centerY, List<UserEntity> devices);
}
