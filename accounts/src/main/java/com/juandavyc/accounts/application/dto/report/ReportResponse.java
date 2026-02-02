package com.juandavyc.accounts.application.dto.report;

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
public class ReportResponse {

   private ReportMetadataResponse metadata;
   private ClientSummaryResponse client;
   private List<AccountTransactionsResponse> accounts;
   private ReportSummaryResponse summary;

}
