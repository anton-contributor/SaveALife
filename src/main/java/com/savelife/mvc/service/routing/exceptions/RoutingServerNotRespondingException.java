package com.savelife.mvc.service.routing.exceptions;

public class RoutingServerNotRespondingException extends Exception {

    public RoutingServerNotRespondingException() {
    }

    public RoutingServerNotRespondingException(String message) {
        super(message);
    }

    public RoutingServerNotRespondingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoutingServerNotRespondingException(Throwable cause) {
        super(cause);
    }
}
