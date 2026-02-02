package com.juandavyc.core.exceptions;

public class PhoneNumberAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE =
            "PhoneNumber already exists";
    public PhoneNumberAlreadyExistsException(String detail) {
        super(String.format("%s: %s", DEFAULT_MESSAGE, detail));
    }
}
