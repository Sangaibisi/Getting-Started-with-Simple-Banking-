package com.eteration.simplebanking.model.EtarationException;

public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException() {
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message);
        // Using initCause() instead of calling super() because Java 1.5 didn't retrofit IOException
        // with a constructor with Throwable. This was done in Java 1.6
        initCause(cause);
    }
}