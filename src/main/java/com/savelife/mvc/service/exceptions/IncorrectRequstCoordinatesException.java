package com.savelife.mvc.service.exceptions;

public class IncorrectRequstCoordinatesException  extends Exception{

    public IncorrectRequstCoordinatesException() {
    }

    public IncorrectRequstCoordinatesException(String message) {
        super(message);
    }

    public IncorrectRequstCoordinatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRequstCoordinatesException(Throwable cause) {
        super(cause);
    }
}
