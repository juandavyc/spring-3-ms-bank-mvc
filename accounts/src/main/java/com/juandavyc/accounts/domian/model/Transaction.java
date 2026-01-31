package com.juandavyc.accounts.domian.model;


import com.juandavyc.accounts.domian.model.enums.TransactionStatus;
import com.juandavyc.accounts.domian.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private UUID accountId;
    private TransactionType type;
    private BigDecimal amount; // entra o sale
    private TransactionStatus status;
    private LocalDateTime timestamp;

    public Transaction() {
    }

    public Transaction(UUID id, UUID accountId, TransactionType type, BigDecimal amount, TransactionStatus status, LocalDateTime timestamp) {
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.timestamp = timestamp;
    }

    public void create(){
        LocalDateTime now = LocalDateTime.now();
        setStatus(TransactionStatus.PENDING);
        setTimestamp(now);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
