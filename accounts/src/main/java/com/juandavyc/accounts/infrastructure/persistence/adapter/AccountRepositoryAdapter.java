package com.juandavyc.accounts.infrastructure.persistence.adapter;

import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.port.AccountPort;
import com.juandavyc.accounts.infrastructure.persistence.entity.AccountEntity;
import com.juandavyc.accounts.infrastructure.persistence.mapper.AccountPersistenceMapper;
import com.juandavyc.accounts.infrastructure.persistence.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountPort {

    private final AccountRepository repository;
    private final AccountPersistenceMapper mapper;

    @Override
    public Account save(Account account) {
        AccountEntity entity = mapper.toEntity(account);
        AccountEntity entitySaved = repository.save(entity);
        return mapper.toDomain(entitySaved);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        Optional<AccountEntity> entity = repository.findById(id);
        return entity.map(mapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        List<AccountEntity> entities = repository.findAll();
        return entities.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID accountId) {
        Optional<AccountEntity> optionalAccount = repository.findById(accountId);
        if (optionalAccount.isPresent()) {
            AccountEntity entity = optionalAccount.get();
            entity.setStatus(AccountStatus.INACTIVE);

            repository.save(entity);
        }
        //repository.save(entity);
    }
}
