package io.github.mateusbosquetti.picpayapi.infra.exception;

public class UnauthorizedTransactionException extends RuntimeException {

    public UnauthorizedTransactionException() {
        super();
    }

    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
