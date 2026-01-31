package com.juandavyc.accounts.application.service;

import com.juandavyc.accounts.application.dto.AccountCommand;
import com.juandavyc.accounts.application.dto.AccountResponse;
import com.juandavyc.accounts.application.mapper.AccountApplicationMapper;
import com.juandavyc.accounts.application.mapper.AccountApplicationUpdateMapper;
import com.juandavyc.accounts.application.usecases.AccountUseCase;
import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.port.AccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountUseCase {

    private final AccountPort port;
    private final AccountApplicationMapper mapper;
    private final AccountApplicationUpdateMapper mapperUpdate;

    @Override
    public AccountResponse create(AccountCommand command) {
        Account account = mapper.toDomain(command);
        // exists verify
        // throws
        // account. createdAt
        account.create();
        Account saved = port.save(account);
        return mapper.toResponse(saved);
    }

    @Override
    public AccountResponse searchById(UUID id) {
        Account account = port.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return mapper.toResponse(account);
    }

    @Override
    public AccountResponse searchByClientId(UUID id) {
        return null;
    }

    @Override
    public List<AccountResponse> search() {
        List<Account> accounts = port.findAll();
        return accounts.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public AccountResponse update(UUID id, AccountCommand command) {
        Account account = port.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        // mapper
        mapperUpdate.updateFromCommand(command, account);
        Account saved = port.save(account);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(UUID id) {
        Account account = port.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        port.deleteById(id);
    }
}
