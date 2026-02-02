package com.juandavyc.accounts.infrastructure.feign.mapper;

import com.juandavyc.accounts.infrastructure.feign.dto.ClientRestResponse;
import com.juandavyc.core.dto.application.ClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRestMapper {

    ClientResponse toDomain(ClientRestResponse response);

}
