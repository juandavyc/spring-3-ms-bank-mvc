package com.juandavyc.accounts.infrastructure.rest.dto;

import com.juandavyc.accounts.domian.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRestRequest {

    private UUID accountId;
    private TransactionType type;
    private BigDecimal amount; // entra o sale

}
