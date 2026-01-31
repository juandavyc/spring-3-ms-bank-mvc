package com.juandavyc.accounts.application.usecases;

import com.juandavyc.accounts.application.dto.TransactionCommand;
import com.juandavyc.accounts.application.dto.TransactionResponse;

import java.util.List;
import java.util.UUID;

public interface TransactionUseCase {

    TransactionResponse create(TransactionCommand command);

    TransactionResponse searchById(UUID id);
    TransactionResponse searchByClientId(UUID id);

    List<TransactionResponse> search();


}
