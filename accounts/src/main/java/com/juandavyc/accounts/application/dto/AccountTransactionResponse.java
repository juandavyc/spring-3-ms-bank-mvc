package com.juandavyc.accounts.application.dto;

import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class AccountTransactionResponse {

    private UUID id;
    private String number;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;

    private List<TransactionResponse> transactions;

    private AccountSummaryResponse summary;
}
