package com.juandayvc.clients.domain.port;

import com.juandayvc.clients.domain.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientPort {

    Client save(Client client);

    Optional<Client> findById(UUID id);

    List<Client> findAll();

    void deleteById(UUID id);

}
