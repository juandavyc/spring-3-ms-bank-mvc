package com.juandavyc.accounts.application.usecases;

import com.juandavyc.accounts.application.dto.account.AccountCommand;
import com.juandavyc.accounts.application.dto.account.AccountResponse;

import java.util.List;
import java.util.UUID;

public interface AccountUseCase {

    AccountResponse create(AccountCommand command);
    AccountResponse findById(UUID id);

    List<AccountResponse> findAll();

    void delete(UUID id);

}
