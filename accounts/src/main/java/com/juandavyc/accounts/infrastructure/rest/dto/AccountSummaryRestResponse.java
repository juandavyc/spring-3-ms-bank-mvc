package com.juandavyc.accounts.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
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
public class AccountSummaryRestResponse {

    private Integer totalApproved;
    private Integer totalRejected;
    private Integer totalTransactions;
    private BigDecimal totalDeposits;
    private BigDecimal totalWithdrawals;
}
