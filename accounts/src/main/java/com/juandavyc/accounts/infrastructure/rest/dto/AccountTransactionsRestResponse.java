package com.juandavyc.accounts.infrastructure.rest.dto;

import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AccountTransactionsRestResponse {

    private UUID id;
    private String number;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;

    private List<TransactionRestResponse> transactions;

    private AccountSummaryRestResponse summary;


}
