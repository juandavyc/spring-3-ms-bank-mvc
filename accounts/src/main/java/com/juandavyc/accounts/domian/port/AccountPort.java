package com.juandavyc.accounts.domian.port;

import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.enums.AccountType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountPort {

    // C,R
    Account save(Account account);

    Optional<Account> findById(UUID id);

    boolean existsByClientIdAndType(UUID clientId, AccountType type);

    List<Account> findByClientId(UUID id);

    List<Account> findAll();

    void deleteById(UUID accountId);

}
