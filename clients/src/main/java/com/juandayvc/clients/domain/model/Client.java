package com.juandayvc.clients.domain.model;

import com.juandayvc.clients.domain.model.enums.ClientStatus;
import com.juandayvc.clients.domain.model.enums.GenderType;

import java.util.UUID;

public class Client extends Person {

    private UUID id;
    private String password;
    private ClientStatus status;

    public Client(
            UUID id,
            String fullName,
            GenderType gender,
            Integer age,
            String identification,
            String address,
            String phoneNumber,
            String password,
            ClientStatus status
    ) {
        super(fullName, gender, age, identification, address, phoneNumber);
        this.id = id;
        this.password = password;
        this.status = status;

    }

    public void deactivate() {
        this.status = ClientStatus.INACTIVE;
    }

    public void activate() {
        this.status = ClientStatus.INACTIVE;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }
}
