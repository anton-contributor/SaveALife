package com.savelife.mvc.apis.detection;

/**
 * Created by anton on 12.08.16.
 */
public interface Detection<T> {

   boolean detect(T radius, T centerX, T centerY, T pointX, T pointY);

}
