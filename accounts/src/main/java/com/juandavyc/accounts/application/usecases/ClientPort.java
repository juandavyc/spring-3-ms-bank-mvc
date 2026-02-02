package com.juandavyc.accounts.application.usecases;

import com.juandavyc.core.dto.application.ClientResponse;

import java.util.UUID;

public interface ClientPort {
    void getClientById(UUID clientId);
    ClientResponse getClientByIdForReport(UUID clientId);
}
