package com.juandavyc.clients.infrastructure.rest.dto;

import com.juandavyc.core.validation.ValidationGroup;
import com.juandavyc.clients.domain.model.enums.GenderType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRestRequest {


    @NotBlank(
            groups = ValidationGroup.OnCreate.class,
            message = "El nombre completo es obligatorio"
    )
    @Size(
            max = 255,
            groups = {ValidationGroup.OnCreate.class, ValidationGroup.OnUpdate.class},
            message = "El nombre completo no puede superar los 255 caracteres"
    )
    private String fullName;

    @NotNull(
            groups = ValidationGroup.OnCreate.class,
            message = "El género es obligatorio"
    )
    private GenderType gender;

    @NotNull(
            groups = ValidationGroup.OnCreate.class,
            message = "La edad es obligatoria"
    )
    @Min(
            value = 0,
            groups = {ValidationGroup.OnCreate.class, ValidationGroup.OnUpdate.class},
            message = "La edad no puede ser negativa"
    )
    @Max(
            value = 120,
            groups = {ValidationGroup.OnCreate.class, ValidationGroup.OnUpdate.class},
            message = "La edad no puede ser mayor a 120 años"
    )
    private Integer age;

    @NotBlank(
            groups = ValidationGroup.OnCreate.class,
            message = "La identificación es obligatoria"
    )
    @Null(
            groups = ValidationGroup.OnUpdate.class,
            message = "La identificación no se puede actualizar"
    )
    @Size(
            max = 50,
            groups = {ValidationGroup.OnCreate.class, ValidationGroup.OnUpdate.class},
            message = "La identificación no puede superar los 50 caracteres"
    )
    private String identification;

    @NotBlank(
            groups = ValidationGroup.OnCreate.class,
            message = "La dirección es obligatoria"
    )
    @Size(
            max = 255,
            groups = {ValidationGroup.OnCreate.class, ValidationGroup.OnUpdate.class},
            message = "La dirección no puede superar los 255 caracteres"
    )
    private String address;

    @NotBlank(
            groups = ValidationGroup.OnCreate.class,
            message = "El número de teléfono es obligatorio"
    )
    @Size(
            max = 20,
            groups = {ValidationGroup.OnCreate.class, ValidationGroup.OnUpdate.class},
            message = "El número de teléfono no puede superar los 20 caracteres"
    )
    private String phoneNumber;

    @NotBlank(
            groups = ValidationGroup.OnCreate.class,
            message = "La contraseña es obligatoria"
    )
    @Size(
            min = 8,
            max = 100,
            groups = ValidationGroup.OnCreate.class,
            message = "La contraseña debe tener entre 8 y 100 caracteres"
    )
    private String password;


}
