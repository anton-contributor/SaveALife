package com.savelife.mvc.apis.detection;

/**
 * Created by anton on 12.08.16.
 */
public interface Detection<T, R> {

    R detect(T radius, T centerX, T centerY, R devices);

}
