package com.juandavyc.accounts.application.dto;

import com.juandavyc.accounts.domian.model.enums.TransactionStatus;
import com.juandavyc.accounts.domian.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionResponse {

    private UUID id;
    private UUID accountId;
    private TransactionType type;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime timestamp;


}
