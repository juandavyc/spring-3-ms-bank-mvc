package com.juandavyc.core.shared;

public enum TransactionResponseCode implements ResponseCode{

    CREATED(201, "Transaccion creada correctamente"),
    NOT_FOUND(404, "Transaccion no encontrada"),
    SUCCESS(200, "Solicitud procesada exitosamente"),
    REJECTED(422, "Transaccion rechazada, saldo insuficiente"),
    DELETED(200, "Transaccion eliminado correctamente");

    private final int value;
    private final String message;

    TransactionResponseCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() { return value; }
    public Enum<?> getStatus() { return this; } // o cambia según tu diseño
    public String getMessage() { return message; }
}
