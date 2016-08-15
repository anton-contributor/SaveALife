package com.savelife.mvc.service.routing.exceptions;

public class IncorrectRequestCoordinatesException  extends Exception{

    public IncorrectRequestCoordinatesException() {
    }

    public IncorrectRequestCoordinatesException(String message) {
        super(message);
    }

    public IncorrectRequestCoordinatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRequestCoordinatesException(Throwable cause) {
        super(cause);
    }
}
