package com.juandavyc.core.shared;

public enum ClientResponseCode implements ResponseCode{

    CREATED(201, "Cuenta creada correctamente"),
    NOT_FOUND(404, "Cuenta no encontrada"),
    SUCCESS(200, "Solicitud procesada exitosamente"),
    UPDATE_FAILED(417, "Update operation failed"),       // 417 = Expectation Failed
    DELETE_FAILED(417, "Delete operation failed"),
    INTERNAL_ERROR(400, "An error occurred"),           // 400 = Bad Request
    AVAILABLE(200, "Resource is available"),
    EXISTS(409, "Resource already exists"),            // 409 = Conflict
    BAD_REQUEST(400, "Invalid request parameters");

    private final int value;
    private final String message;

    ClientResponseCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() { return value; }
    public Enum<?> getStatus() { return this; } // o cambia según tu diseño
    public String getMessage() { return message; }
}
