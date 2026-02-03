package com.juandavyc.clients.infrastructure.persistence.adapter;

import com.juandavyc.clients.domain.model.Client;
import com.juandavyc.clients.domain.model.enums.ClientStatus;
import com.juandavyc.clients.domain.port.ClientPort;
import com.juandavyc.clients.infrastructure.persistence.entity.ClientEntity;
import com.juandavyc.clients.infrastructure.persistence.mapper.ClientPersistenceMapper;
import com.juandavyc.clients.infrastructure.persistence.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientPort {

    private final ClientRepository repository;
    private final ClientPersistenceMapper mapper;

    @Override
    public Client save(Client client) {
        ClientEntity entity = mapper.toEntity(client);
        ClientEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        Optional<ClientEntity> entity = repository.findById(id);
        return entity.map(mapper::toDomain);
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        return repository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existByIdentification(String identification) {
        return repository.existsByIdentification(identification);
    }

    @Override
    public boolean existsByPhoneNumberAndIdNot(String phoneNumber, UUID id) {
        return repository.existsByPhoneNumberAndIdNot(phoneNumber, id);
    }


    @Override
    public Client update(Client client) {
        ClientEntity entity = mapper.toEntity(client);
        ClientEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Client> findAll() {

        List<ClientEntity> entities = repository.findAll();
        return entities.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(UUID id) {
        Optional<ClientEntity> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            ClientEntity client = optionalClient.get();
            client.setStatus(ClientStatus.INACTIVE);
            repository.save(client);
        }
    }

}
