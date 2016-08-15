package com.savelife.mvc.service.massaging.connection.HTTPConnection;

import java.io.UnsupportedEncodingException;

/**
 * Created by anton on 01.08.16.
 */
public interface MassagingConnection {
    /*
    * abstract method echo to send the massage
    **/
    String echo(String body) throws UnsupportedEncodingException;

}
