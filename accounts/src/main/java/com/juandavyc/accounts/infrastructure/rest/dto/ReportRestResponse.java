package com.juandavyc.accounts.infrastructure.rest.dto;

import com.juandavyc.accounts.application.dto.report.AccountTransactionsResponse;
import com.juandavyc.accounts.application.dto.report.ClientSummaryResponse;
import com.juandavyc.accounts.application.dto.report.ReportMetadataResponse;
import com.juandavyc.accounts.application.dto.report.ReportSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ReportRestResponse {

    private ReportMetadataResponse metadata;
    private ClientSummaryResponse client;
    private List<AccountTransactionsResponse> accounts;
    private ReportSummaryResponse summary;

}


