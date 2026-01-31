package com.juandayvc.clients.infrastructure.rest.mapper;

import com.juandayvc.clients.application.dto.ClientCommand;
import com.juandayvc.clients.application.dto.ClientResponse;
import com.juandayvc.clients.infrastructure.rest.dto.ClientRestRequest;
import com.juandayvc.clients.infrastructure.rest.dto.ClientRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRestMapper {

    ClientCommand toCommand(ClientRestRequest request);

    ClientRestResponse toRestResponse(ClientResponse response);

}
