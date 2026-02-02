package com.juandavyc.core.shared;

public interface ResponseCode {
    int getValue();
    Enum<?> getStatus();
    String getMessage();
}
