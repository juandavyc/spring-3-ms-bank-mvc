package com.juandavyc.core.exceptions;

public class IdentificationAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE =
            "Identification already exists";
    public IdentificationAlreadyExistsException(String detail) {
        super(String.format("%s: %s", DEFAULT_MESSAGE, detail));
    }
}
