package com.juandavyc.clients.application.mapper;

import com.juandavyc.clients.application.dto.ClientCommand;
import com.juandavyc.clients.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClientApplicationUpdateMapper {

    void updateFromCommand(ClientCommand command, @MappingTarget Client client);

}
