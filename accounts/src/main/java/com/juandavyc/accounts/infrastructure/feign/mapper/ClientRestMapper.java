package com.juandavyc.accounts.infrastructure.feign.mapper;

import com.juandavyc.accounts.application.dto.ClientResponse;
import com.juandavyc.accounts.infrastructure.feign.dto.ClientRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRestMapper {

    ClientResponse toDomain(ClientRestResponse response);

}
