package com.juandavyc.accounts.application.dto.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportSummaryResponse {

    private int totalAccounts;
    private int totalApprovedTransactions;
    private int totalRejectedTransactions;
    private int totalTransactions;

}
