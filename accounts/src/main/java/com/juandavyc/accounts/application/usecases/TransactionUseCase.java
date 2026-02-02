package com.juandavyc.accounts.application.usecases;

import com.juandavyc.accounts.application.dto.transaction.TransactionCommand;
import com.juandavyc.accounts.application.dto.transaction.TransactionResponse;

import java.util.List;
import java.util.UUID;

public interface TransactionUseCase {

    TransactionResponse create(TransactionCommand command);

    TransactionResponse searchById(UUID id);
    List<TransactionResponse> findByAccountId(UUID id);

    List<TransactionResponse> findAll();


}
