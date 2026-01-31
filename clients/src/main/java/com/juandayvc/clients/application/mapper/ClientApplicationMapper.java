package com.juandayvc.clients.application.mapper;

import com.juandayvc.clients.application.dto.ClientCommand;
import com.juandayvc.clients.application.dto.ClientResponse;
import com.juandayvc.clients.domain.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientApplicationMapper {

    Client toDomain(ClientCommand command);

    ClientResponse toResponse(Client client);

}
