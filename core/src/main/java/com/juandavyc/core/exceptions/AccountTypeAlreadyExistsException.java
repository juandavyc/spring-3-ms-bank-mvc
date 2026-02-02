package com.juandavyc.core.exceptions;

public class AccountTypeAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE =
            "Este cliente ya tiene una cuenta de tipo";

    public AccountTypeAlreadyExistsException(String message) {
        super(String.format("%s: %s", DEFAULT_MESSAGE, message));

    }
}
