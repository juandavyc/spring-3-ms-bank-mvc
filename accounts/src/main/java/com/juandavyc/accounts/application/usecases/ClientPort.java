package com.juandavyc.accounts.application.usecases;

import com.juandavyc.accounts.application.dto.ClientResponse;

import java.util.Optional;
import java.util.UUID;

public interface ClientPort {
    ClientResponse getClientById(UUID clientId);
    ClientResponse getClientByIdForReport(UUID clientId);
}
