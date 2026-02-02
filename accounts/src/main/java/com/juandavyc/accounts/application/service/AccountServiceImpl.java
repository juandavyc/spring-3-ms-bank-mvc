package com.juandavyc.accounts.application.service;

import com.juandavyc.accounts.application.dto.account.AccountCommand;
import com.juandavyc.accounts.application.dto.account.AccountResponse;
import com.juandavyc.accounts.application.mapper.AccountApplicationMapper;
import com.juandavyc.accounts.application.usecases.AccountUseCase;
import com.juandavyc.accounts.application.usecases.ClientPort;
import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.port.AccountPort;
import com.juandavyc.core.exceptions.AccountTypeAlreadyExistsException;
import com.juandavyc.core.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountUseCase {

    private final AccountPort port;
    private final AccountApplicationMapper mapper;

    private final ClientPort clientPort;

    @Override
    public AccountResponse create(AccountCommand command) {

        clientPort.getClientById(command.getClientId());

        boolean existsByClientIdAndType = port.existsByClientIdAndType(command.getClientId(), command.getType());

        if (existsByClientIdAndType){
            throw new AccountTypeAlreadyExistsException(command.getType().toString());
        }

        Account account = mapper.toDomain(command);
        account.initialize();
        Account saved = port.save(account);
        return mapper.toResponse(saved);

    }

    @Override
    public AccountResponse findById(UUID id) {
        Account account = port.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Account", "id", id));
        return mapper.toResponse(account);
    }

    @Override
    public List<AccountResponse> findAll() {
        List<Account> accounts = port.findAll();
        return accounts.stream()
                .map(mapper::toResponse)
                .toList();
    }


    @Override
    public void delete(UUID id) {
        Account account = port.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Account", "id", id));
        account.setStatus(AccountStatus.INACTIVE);
        port.deleteById(id);
    }
}
