package com.savelife.mvc.service.detection;

import com.savelife.mvc.apis.detection.Detection;
import com.savelife.mvc.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anton on 18.08.16.
 */
@Service("detectService")
public class DetectServiceImpl implements DetectService<Double>{

    @Autowired
    private Detection detect;


    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> detect(Double radius, Double centerX, Double centerY, List<UserEntity> devices) {
        return (List<UserEntity>) detect.detect(radius, centerX, centerY,devices);
    }
}
