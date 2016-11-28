package com.savelife.mvc.apis.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Functional interface to convert the object to object
 */
//@FunctionalInterface
public interface Converter<P, R> {

    List<R> convert(List<P> p, Map map);
}
