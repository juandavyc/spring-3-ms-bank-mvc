package com.juandavyc.accounts.infrastructure.persistence.repository;

import com.juandavyc.accounts.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findByAccountId(UUID accountId);

    List<TransactionEntity> findByAccountIdAndTimestampBetween(
            UUID accountId,
            LocalDateTime start,
            LocalDateTime end
    );

}
