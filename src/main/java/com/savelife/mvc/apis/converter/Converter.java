package com.savelife.mvc.apis.converter;

/**
 * Functional interface to convert the object to object
 */
@FunctionalInterface
public interface Converter<P,R> {

    R convert(P p);
}
