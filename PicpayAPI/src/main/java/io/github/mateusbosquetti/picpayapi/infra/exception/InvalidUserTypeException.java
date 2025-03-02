package io.github.mateusbosquetti.picpayapi.infra.exception;

public class InvalidUserTypeException extends RuntimeException{

    public InvalidUserTypeException() {
        super();
    }

    public InvalidUserTypeException(String message) {
        super(message);
    }
}
