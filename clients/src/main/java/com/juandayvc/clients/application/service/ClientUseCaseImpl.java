package com.juandayvc.clients.application.service;

import com.juandavyc.core.exceptions.IdentificationAlreadyExistsException;
import com.juandavyc.core.exceptions.PhoneNumberAlreadyExistsException;
import com.juandavyc.core.exceptions.ResourceNotFoundException;
import com.juandayvc.clients.application.dto.ClientCommand;
import com.juandayvc.clients.application.dto.ClientResponse;
import com.juandayvc.clients.application.mapper.ClientApplicationMapper;
import com.juandayvc.clients.application.mapper.ClientApplicationUpdateMapper;
import com.juandayvc.clients.application.usecases.ClientUseCase;
import com.juandayvc.clients.application.usecases.PasswordHasher;
import com.juandayvc.clients.domain.model.Client;
import com.juandayvc.clients.domain.model.enums.ClientStatus;
import com.juandayvc.clients.domain.port.ClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientPort port;
    private final ClientApplicationMapper mapper;
    private final ClientApplicationUpdateMapper updateMapper;

    private final PasswordHasher passwordHasher;

    @Override
    public ClientResponse create(ClientCommand command) {

        Client client = mapper.toDomain(command);

        if (port.existByIdentification(command.getIdentification())) {
            throw new IdentificationAlreadyExistsException(command.getIdentification());
        }

        if (port.existByPhoneNumber(command.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(command.getPhoneNumber());
        }

        client.setPassword(
                passwordHasher.hash(command.getPassword())
        );

        client.setStatus(ClientStatus.ACTIVE);
        Client saved = port.save(client);
        return mapper.toResponse(saved);

    }

    @Override
    public ClientResponse findById(UUID id) {

        Client client = port.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        return mapper.toResponse(client);

    }

    @Override
    public ClientResponse update(UUID id, ClientCommand command) {

        Client client = port.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));

        if (port.existsByPhoneNumberAndIdNot(command.getPhoneNumber(), id)) {
            throw new PhoneNumberAlreadyExistsException(command.getPhoneNumber());
        }

        updateMapper.updateFromCommand(command, client);

        if (!Objects.isNull(command.getPassword())) {
            client.setPassword(passwordHasher.hash(command.getPassword()));
        }

        Client saved = port.save(client);
        return mapper.toResponse(saved);

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

        Client client = port.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));

        client.deactivate();
        port.save(client);

    }

}
