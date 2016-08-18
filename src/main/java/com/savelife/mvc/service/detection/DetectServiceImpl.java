package com.savelife.mvc.service.detection;

import com.savelife.mvc.apis.detection.Detection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by anton on 18.08.16.
 */
@Service("detectService")
public class DetectServiceImpl implements DetectService{

    @Autowired
    private Detection detect;

    @Override
    public boolean detect(Double radius, Double centerX, Double centerY, Double pointX, Double pointY) {
        return detect.detect(radius,centerX,centerY,pointX,pointY);
    }
}
