package com.juandavyc.core.shared;

public enum AccountResponseCode implements ResponseCode{

    CREATED(201, "Cuenta creada correctamente"),
    NOT_FOUND(404, "Cuenta no encontrada"),
    SUCCESS(200, "Solicitud procesada exitosamente"),
    DELETED(200, "Cuenta eliminada correctamente");;

    private final int value;
    private final String message;

    AccountResponseCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() { return value; }
    public Enum<?> getStatus() { return this; } // o cambia según tu diseño
    public String getMessage() { return message; }
}
