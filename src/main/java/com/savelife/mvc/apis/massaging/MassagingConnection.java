package com.savelife.mvc.apis.massaging;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by anton on 01.08.16.
 */
public interface MassagingConnection {
    /*
    * abstract method echo to send the massage
    **/
    List<String> send(List<String> body) throws UnsupportedEncodingException;

}
