package com.juandavyc.accounts.infrastructure.persistence.adapter;

import com.juandavyc.accounts.domian.model.Transaction;
import com.juandavyc.accounts.domian.port.TransactionPort;
import com.juandavyc.accounts.infrastructure.persistence.entity.TransactionEntity;
import com.juandavyc.accounts.infrastructure.persistence.mapper.TransactionPersistenceMapper;
import com.juandavyc.accounts.infrastructure.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionPort {

    private final TransactionRepository repository;
    private final TransactionPersistenceMapper mapper;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = mapper.toEntity(transaction);
        TransactionEntity result = repository.save(entity);
        return mapper.toDomain(result);
    }

    @Override
    public Optional<Transaction> findById(UUID accountId) {
        Optional<TransactionEntity> transaction = repository.findById(accountId);
        return transaction.map(mapper::toDomain);
    }

    @Override
    public List<Transaction> findAll() {
        List<TransactionEntity> transactions = repository.findAll();
        return transactions.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}
