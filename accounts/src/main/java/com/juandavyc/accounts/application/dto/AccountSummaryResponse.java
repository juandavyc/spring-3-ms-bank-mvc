package com.juandavyc.accounts.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AccountSummaryResponse {

    private Integer totalApproved;
    private Integer totalRejected;
    private Integer totalTransactions;
    private BigDecimal totalDeposits;
    private BigDecimal totalWithdrawals;

}
