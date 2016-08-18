package com.savelife.mvc.service.sender;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by anton on 10.08.16.
 */
public interface SenderService<T extends String> {

    List<T> echo(List<T> body) throws UnsupportedEncodingException;

}
