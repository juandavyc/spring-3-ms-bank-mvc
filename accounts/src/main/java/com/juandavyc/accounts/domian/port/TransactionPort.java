package com.juandavyc.accounts.domian.port;

import com.juandavyc.accounts.domian.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionPort {

    Transaction save(Transaction transaction);

    Optional<Transaction> findById(UUID accountId);

    List<Transaction> findAll();

}
