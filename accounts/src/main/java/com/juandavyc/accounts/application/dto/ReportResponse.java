package com.juandavyc.accounts.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportResponse {

   private ReportMetadata metadata;
   private ClientResponse client;
   private List<AccountTransactionResponse> accountTransactions;
   private ReportSummary reportSummary;

   @NoArgsConstructor
   @Getter
   @Setter
   public static class ReportMetadata {

      private LocalDate startDate;
      private LocalDate endDate;
      private LocalDateTime generatedAt;

   }

   @NoArgsConstructor
   @Getter
   @Setter
   public static class ReportSummary {
      private int totalAccounts;
      private int totalApprovedTransactions;
      private int totalRejectedTransactions;
      private int totalTransactions;
      private BigDecimal totalBalance;

   }

}
