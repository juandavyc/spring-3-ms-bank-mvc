package com.juandavyc.accounts.application.dto.report;

import com.juandavyc.accounts.application.dto.transaction.TransactionResponse;
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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AccountTransactionsResponse {

    private UUID id;
    private String number;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;
    private List<TransactionResponse> transactions;
    private AccountSummaryResponse summary;

}
