package com.juandavyc.clients.infrastructure.rest.mapper;

import com.juandavyc.clients.application.dto.ClientCommand;
import com.juandavyc.clients.application.dto.ClientResponse;
import com.juandavyc.clients.infrastructure.rest.dto.ClientRestRequest;
import com.juandavyc.clients.infrastructure.rest.dto.ClientRestResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientRestMapper {

    ClientCommand toCommand(ClientRestRequest request);

    ClientRestResponse toRestResponse(ClientResponse response);

    List<ClientRestResponse>  toRestResponseList(List<ClientResponse> requests);

}
