package com.juandavyc.clients.application.mapper;

import com.juandavyc.clients.application.dto.ClientCommand;
import com.juandavyc.clients.application.dto.ClientResponse;
import com.juandavyc.clients.domain.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientApplicationMapper {

    Client toDomain(ClientCommand command);

    ClientResponse toResponse(Client client);


}
