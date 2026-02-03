package com.juandavyc.clients.application.usecases;

import com.juandavyc.clients.application.dto.ClientCommand;
import com.juandavyc.clients.application.dto.ClientResponse;

import java.util.List;
import java.util.UUID;

public interface ClientUseCase {

    ClientResponse create(ClientCommand command);

    ClientResponse findById(UUID id);

    ClientResponse update(UUID id, ClientCommand command);

    List<ClientResponse> findAll();

    void deleteById(UUID id);

}
