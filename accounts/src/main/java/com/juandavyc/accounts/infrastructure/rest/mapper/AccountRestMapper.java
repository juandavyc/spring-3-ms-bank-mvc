package com.juandavyc.accounts.infrastructure.rest.mapper;

import com.juandavyc.accounts.application.dto.account.AccountCommand;
import com.juandavyc.accounts.application.dto.account.AccountResponse;
import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestRequest;
import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountRestMapper {

    AccountCommand toCommand(AccountRestRequest request);
    // update
    AccountRestResponse toRestResponse(AccountResponse response);

}
