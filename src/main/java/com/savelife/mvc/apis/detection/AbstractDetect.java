package com.savelife.mvc.apis.detection;

import org.springframework.stereotype.Component;

/**
 * Created by anton on 22.08.16.
 */
@Component
public abstract class AbstractDetect {

    protected boolean detection(Double radius, Double centerX, Double centerY, Double pointX, Double pointY) {
        return (Math.pow(centerX - pointX, 2)
                + Math.pow(centerY - pointY, 2)
                <= Math.pow(radius, 2));
    }
}
