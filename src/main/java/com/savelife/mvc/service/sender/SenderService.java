package com.savelife.mvc.service.sender;

import java.io.UnsupportedEncodingException;

/**
 * Created by anton on 10.08.16.
 */
public interface SenderService<T extends String> {

    T echo(T body) throws UnsupportedEncodingException;

}
