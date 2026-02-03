package com.juandavyc.clients.infrastructure.persistence.mapper;

import com.juandavyc.clients.domain.model.Client;
import com.juandavyc.clients.infrastructure.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientPersistenceMapper {

    ClientEntity toEntity(Client client);

    Client toDomain(ClientEntity clientEntity);

}
