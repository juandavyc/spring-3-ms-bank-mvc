package com.juandavyc.accounts.infrastructure.rest.dto;

import com.juandavyc.accounts.domian.model.enums.TransactionType;
import com.juandavyc.core.validation.ValidationGroup;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRestRequest {

    @NotNull(message = "El accountId es obligatorio", groups = ValidationGroup.OnCreate.class)
    private UUID accountId;

    @NotNull(message = "El tipo de transacción es obligatorio", groups = ValidationGroup.OnCreate.class)
    private TransactionType type;

    @NotNull(message = "El monto es obligatorio", groups = ValidationGroup.OnCreate.class)
    @DecimalMin(
            value = "0.01",
            message = "El monto debe ser mayor a 0",
            groups = ValidationGroup.OnCreate.class
    )
    @Digits(
            integer = 18,
            fraction = 2,
            message = "El monto puede tener máximo 18 dígitos enteros y 2 decimales",
            groups = ValidationGroup.OnCreate.class
    )
    private BigDecimal amount;

}
