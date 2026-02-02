package com.juandavyc.accounts.application.service;

import com.juandavyc.accounts.application.dto.transaction.TransactionCommand;
import com.juandavyc.accounts.application.dto.transaction.TransactionResponse;
import com.juandavyc.accounts.application.mapper.TransactionApplicationMapper;
import com.juandavyc.accounts.application.usecases.TransactionUseCase;
import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.Transaction;
import com.juandavyc.accounts.domian.port.AccountPort;
import com.juandavyc.accounts.domian.port.TransactionPort;
import com.juandavyc.core.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionUseCase {

    private final TransactionPort port;
    private final TransactionApplicationMapper mapper;

    private final AccountPort accountPort;

    @Override
    public TransactionResponse create(TransactionCommand command) {

        Account account = accountPort.findById(command.getAccountId())
                .orElseThrow(() ->new ResourceNotFoundException("Account", "id", command.getAccountId()));

        Transaction transaction = mapper.toDomain(command);
        BigDecimal currentBalance = account.getBalance();
        transaction.initialize();
        BigDecimal newBalance = transaction.calculateNewBalance(currentBalance);
        transaction.evaluate(newBalance);

        if (transaction.isApproved()) {
            account.setBalance(newBalance);
            accountPort.save(account);
        }
        else{

        }

        Transaction saved = port.save(transaction);
        return mapper.toResponse(saved);
    }


    @Override
    public TransactionResponse searchById(UUID id) {
        Transaction transaction = port.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Transaction", "id", id));
        return mapper.toResponse(transaction);
    }

    @Override
    public List<TransactionResponse> findByAccountId(UUID id) {
        List<Transaction> transactions = port.findByAccountId(id);
        return transactions.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<TransactionResponse> findAll() {
        List<Transaction> transactions = port.findAll();
        return transactions.stream()
                .map(mapper::toResponse)
                .toList();
    }

}
