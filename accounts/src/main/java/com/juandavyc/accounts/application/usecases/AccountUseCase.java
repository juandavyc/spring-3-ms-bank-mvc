package com.juandavyc.accounts.application.usecases;

import com.juandavyc.accounts.application.dto.AccountCommand;
import com.juandavyc.accounts.application.dto.AccountResponse;

import java.util.List;
import java.util.UUID;

public interface AccountUseCase {

    AccountResponse create(AccountCommand command);
    AccountResponse searchById(UUID id);

    List<AccountResponse>  findByClientId(UUID id);

    List<AccountResponse> search();

    AccountResponse update(UUID id, AccountCommand command);

    void delete(UUID id);

}
