package com.juandavyc.accounts.application.mapper;

import com.juandavyc.accounts.application.dto.AccountCommand;
import com.juandavyc.accounts.domian.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountApplicationUpdateMapper {

    void updateFromCommand(AccountCommand command, @MappingTarget Account account);

}
