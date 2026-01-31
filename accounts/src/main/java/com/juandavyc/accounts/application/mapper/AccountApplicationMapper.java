package com.juandavyc.accounts.application.mapper;

import com.juandavyc.accounts.application.dto.AccountCommand;
import com.juandavyc.accounts.application.dto.AccountResponse;
import com.juandavyc.accounts.domian.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountApplicationMapper {

    Account toDomain(AccountCommand command);

    AccountResponse toResponse(Account account);

}
