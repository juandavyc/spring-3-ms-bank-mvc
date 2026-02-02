package com.juandayvc.clients.domain.port;

import com.juandayvc.clients.domain.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientPort {

    Client save(Client client);

    Optional<Client> findById(UUID id);

    boolean existByPhoneNumber(String phoneNumber);

    boolean existByIdentification(String identification);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, UUID id);


    Client update(Client client);

    List<Client> findAll();

    void deleteById(UUID id);

}
