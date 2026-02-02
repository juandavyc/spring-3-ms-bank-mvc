package com.juandavyc.accounts.application.service;

import com.juandavyc.accounts.application.dto.TransactionCommand;
import com.juandavyc.accounts.application.dto.TransactionResponse;
import com.juandavyc.accounts.application.mapper.TransactionApplicationMapper;
import com.juandavyc.accounts.application.usecases.TransactionUseCase;
import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.Transaction;
import com.juandavyc.accounts.domian.port.AccountPort;
import com.juandavyc.accounts.domian.port.TransactionPort;
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

        Transaction transaction = mapper.toDomain(command);

        Account account = accountPort.findById(command.getAccountId())
                .orElseThrow(() -> new RuntimeException("no cuenta existente"));

        BigDecimal currentBalance = account.getBalance();

        transaction.initialize();

        BigDecimal newBalance = transaction.calculateNewBalance(currentBalance);

        transaction.evaluate(newBalance);

        if (transaction.isApproved()) {
            account.setBalance(newBalance);
            accountPort.save(account);
        }

        Transaction saved = port.save(transaction);
        return mapper.toResponse(saved);
    }


    @Override
    public TransactionResponse searchById(UUID id) {
        Transaction transaction = port.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
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
    public List<TransactionResponse> search() {
        List<Transaction> transactions = port.findAll();
        return transactions.stream()
                .map(mapper::toResponse)
                .toList();
    }

    // utility
}
