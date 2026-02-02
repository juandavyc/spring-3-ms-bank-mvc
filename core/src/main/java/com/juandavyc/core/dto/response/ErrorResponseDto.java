package com.juandavyc.core.dto.response;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Map;

public class ErrorResponseDto {

    private Integer status;
    private String code;
    private String message;
    private Map<String, String> errors;
    private OffsetDateTime timestamp;

    public ErrorResponseDto(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.errors = Collections.emptyMap();
        this.timestamp = OffsetDateTime.now();
    }

    public ErrorResponseDto(Integer status, String code, Map<String, String> errors) {
        this.status = status;
        this.code = code;
        this.message = "Error en la validacion de campos";
        this.errors = errors;
        this.timestamp = OffsetDateTime.now();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
