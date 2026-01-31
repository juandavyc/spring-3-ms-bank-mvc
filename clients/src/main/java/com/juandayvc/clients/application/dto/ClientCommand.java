package com.juandayvc.clients.application.dto;

import com.juandayvc.clients.domain.model.enums.ClientStatus;
import com.juandayvc.clients.domain.model.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientCommand {

    private String fullName;
    private GenderType gender;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;
    private String password;
    private ClientStatus status;

}
