package com.juandayvc.clients.infrastructure.rest.dto;

import com.juandayvc.clients.domain.model.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRestRequest {

    private String fullName;
    private GenderType gender;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;
    private String password;

}
