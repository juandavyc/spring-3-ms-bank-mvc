package com.juandavyc.accounts.application.dto;

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
public class ClientResponse {

    private UUID id;
    private String fullName;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;

}
