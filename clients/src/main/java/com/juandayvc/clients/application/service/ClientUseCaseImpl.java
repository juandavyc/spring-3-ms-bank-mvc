package com.juandayvc.clients.application.service;

import com.juandayvc.clients.application.dto.ClientCommand;
import com.juandayvc.clients.application.dto.ClientResponse;
import com.juandayvc.clients.application.mapper.ClientApplicationMapper;
import com.juandayvc.clients.application.usecases.ClientUseCase;
import com.juandayvc.clients.domain.model.Client;
import com.juandayvc.clients.domain.model.enums.ClientStatus;
import com.juandayvc.clients.domain.port.ClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientPort port;
    private final ClientApplicationMapper mapper;

    @Override
    public ClientResponse create(ClientCommand command) {

        Client client = mapper.toDomain(command);

        client.setStatus(ClientStatus.ACTIVE);
        // initialize
        Client saved = port.save(client);
        return mapper.toResponse(saved);

    }

    @Override
    public ClientResponse findById(UUID id) {

        Client client = port.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return mapper.toResponse(client);

    }

    @Override
    public List<ClientResponse> findAll() {

        List<Client> clients = port.findAll();
        return clients.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(UUID id) {

        Client Client = port.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        port.deleteById(id);

    }

}
