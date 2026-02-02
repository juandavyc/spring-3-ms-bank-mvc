package com.juandavyc.core.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final String TEMPLATE = "%s no encontrado con %s: %s";

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format(TEMPLATE, resource, field, value));
    }
}
