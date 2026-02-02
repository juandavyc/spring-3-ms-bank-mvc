package com.juandavyc.accounts.infrastructure.rest.dto;

import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
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
public class AccountRestRequest {

    @NotNull(
            groups = ValidationGroup.OnCreate.class,
            message = "El balance inicial es obligatorio"
    )
    @DecimalMin(
            value = "0.00",
            inclusive = true,
            groups = ValidationGroup.OnCreate.class,
            message = "El balance inicial no puede ser negativo"
    )
    @Digits(
            integer = 18,
            fraction = 2,
            groups = ValidationGroup.OnCreate.class,
            message = "El balance inicial debe tener máximo 18 enteros y 2 decimales"
    )
    private BigDecimal openingBalance;

    @NotNull(
            groups = ValidationGroup.OnCreate.class,
            message = "El tipo de cuenta es obligatorio"
    )
    private AccountType type;

    @NotNull(
            groups = ValidationGroup.OnCreate.class,
            message = "El cliente es obligatorio"
    )
    private UUID clientId;


}
