package com.juandavyc.core.exceptions;

public class ClientServiceUnavailableException extends RuntimeException {
    private static final String DEFAULT_MESSAGE =
            "No se pudo recuperar el cliente tras varios intentos";

    public ClientServiceUnavailableException(String message) {
        super(String.format("%s ", DEFAULT_MESSAGE, message));

    }
}
