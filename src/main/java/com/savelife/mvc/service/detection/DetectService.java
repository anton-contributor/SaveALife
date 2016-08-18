package com.savelife.mvc.service.detection;

/**
 * Created by anton on 18.08.16.
 */
public interface DetectService {

    boolean detect(Double radius, Double centerX, Double centerY, Double pointX, Double pointY);
}
