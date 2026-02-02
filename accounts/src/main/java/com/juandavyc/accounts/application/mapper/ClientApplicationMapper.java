package com.juandavyc.accounts.application.mapper;

import com.juandavyc.accounts.application.dto.report.ClientSummaryResponse;
import com.juandavyc.core.dto.application.ClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientApplicationMapper {

    ClientSummaryResponse toResponse(ClientResponse client);

}
