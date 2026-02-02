package com.juandavyc.accounts.application.usecases;

import com.juandavyc.core.dto.application.ClientResponse;

import java.util.UUID;

public interface ClientPort {
    ClientResponse getClientById(UUID clientId);
    ClientResponse getClientByIdForReport(UUID clientId);
}
