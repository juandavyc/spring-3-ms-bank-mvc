package com.juandayvc.clients.infrastructure.persistence.mapper;

import com.juandayvc.clients.domain.model.Client;
import com.juandayvc.clients.infrastructure.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientPersistenceMapper {

    ClientEntity toEntity(Client client);

    Client toDomain(ClientEntity clientEntity);

}
