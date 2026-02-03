package com.juandavyc.clients.infrastructure.rest.dto;

import com.juandavyc.clients.domain.model.enums.ClientStatus;
import com.juandavyc.clients.domain.model.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientRestResponse {

    private UUID id;
    private String fullName;
    private GenderType gender;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;
    private ClientStatus status;

}
