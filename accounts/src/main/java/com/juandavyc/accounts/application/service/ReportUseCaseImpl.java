package com.juandavyc.accounts.application.service;

import com.juandavyc.accounts.application.dto.AccountSummaryResponse;
import com.juandavyc.accounts.application.dto.AccountTransactionResponse;
import com.juandavyc.accounts.application.dto.ClientResponse;
import com.juandavyc.accounts.application.dto.ReportResponse;
import com.juandavyc.accounts.application.dto.TransactionResponse;
import com.juandavyc.accounts.application.mapper.TransactionApplicationMapper;
import com.juandavyc.accounts.application.usecases.ClientPort;
import com.juandavyc.accounts.application.usecases.ReportUseCase;
import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.Transaction;
import com.juandavyc.accounts.domian.model.enums.TransactionStatus;
import com.juandavyc.accounts.domian.model.enums.TransactionType;
import com.juandavyc.accounts.domian.port.AccountPort;
import com.juandavyc.accounts.domian.port.TransactionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ReportUseCaseImpl implements ReportUseCase {

    private final ClientPort clientPort;
    private final AccountPort accountPort;
    private final TransactionPort transactionPort;

    private final TransactionApplicationMapper transactionMapper;

    @Override
    public ReportResponse execute(LocalDate startDate, LocalDate endDate, UUID clientId) {
        ReportResponse reportResponse = new ReportResponse();
        ClientResponse client = clientPort.getClientById(clientId);
//        if (client == null) {
//            throw new ClientNotFoundException("Client not found with id: " + clientId);
//        }
        // cambiar por el d fechas

        ReportResponse.ReportMetadata metadata = reportMetadata(
                startDate, endDate
        );
        ReportResponse.ReportSummary summary = new ReportResponse.ReportSummary();


        List<Account> accounts = accountPort.findByClientId(clientId);

        summary.setTotalAccounts(accounts.size());

        List<AccountTransactionResponse> accountTransactionResponses = accounts.stream()
                .map(account -> {

                    AccountTransactionResponse atr = processAccountForReport(account, startDate, endDate);

                    summary.setTotalTransactions(summary.getTotalTransactions() + atr.getSummary().getTotalTransactions());
                    summary.setTotalApprovedTransactions(summary.getTotalApprovedTransactions() + atr.getSummary().getTotalApproved());
                    summary.setTotalRejectedTransactions(summary.getTotalRejectedTransactions() + atr.getSummary().getTotalRejected());
                    //summary.setTotalBalance(summary.getTotalBalance().add(atr.getBalance()));

                    return atr;
                })
                .toList();




        reportResponse.setMetadata(metadata);
        reportResponse.setClient(client);
        reportResponse.setAccountTransactions(accountTransactionResponses);
        reportResponse.setReportSummary(summary);




        return reportResponse;
    }

    private ReportResponse.ReportMetadata reportMetadata(LocalDate startDate, LocalDate endDate){
         ReportResponse.ReportMetadata rep = new ReportResponse.ReportMetadata();
        rep.setStartDate(startDate);
        rep.setEndDate(endDate);
        rep.setGeneratedAt(LocalDateTime.now());

                return rep;
    }

    private AccountTransactionResponse processAccountForReport(
            Account account, LocalDate startDate, LocalDate endDate) {
        //log.debug("Processing account {} for report", account.getId());

        //startDate, endDate
        List<Transaction> transactions = transactionPort
                .findByAccountId(account.getId());


        List<TransactionResponse> transactionResponses =
                transactionMapper.toResponseList(transactions);

        AccountSummaryResponse summaryResponse = calculateAccountMetrics(transactions);

        return AccountTransactionResponse.builder()
                .id(account.getId())
                .number(account.getNumber())
                .type(account.getType())
                .status(account.getStatus())
                //.currentBalance(account.getBalance())
                //.closingBalance(account.getBalance()) // Para reporte pasivo podría ser igual
                .transactions(transactionResponses)   // Del mapper
                .summary(summaryResponse)
                // .metrics(accountMetrics)              // De lógica de negocio
                .build();
    }

    private AccountSummaryResponse calculateAccountMetrics(List<Transaction> transactions) {

        AtomicReference<BigDecimal> totalDeposits = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<BigDecimal> totalWithdrawals = new AtomicReference<>(BigDecimal.ZERO);

        Integer totalTransactions = transactions.size();
        AtomicReference<Integer> totalApproved = new AtomicReference<>(0);
        AtomicReference<Integer> totalRejected = new AtomicReference<>(0);

        transactions
                .forEach(transaction -> {
                    if (transaction.getStatus().equals(TransactionStatus.APPROVED)) {
                        if (transaction.getType().equals(TransactionType.WITHDRAWAL)) {
                            totalWithdrawals.set(totalWithdrawals.get().add(transaction.getAmount()));
                        } else {
                            totalDeposits.set(totalDeposits.get().add(transaction.getAmount()));
                        }
                        totalApproved.getAndSet(totalApproved.get() + 1);
                    } else {
                        totalRejected.getAndSet(totalRejected.get() + 1);
                    }
                });

        return AccountSummaryResponse.builder()
                .totalApproved(totalApproved.get())
                .totalRejected(totalRejected.get())
                .totalTransactions(totalTransactions)
                .totalDeposits(totalDeposits.get())
                .totalWithdrawals(totalWithdrawals.get())
                .build();
    }

//    @Override
//    public AccountSummaryResponse execute(List<Transaction> transactions) {
//
//        AccountSummaryResponse summary = new AccountSummaryResponse();
//
//        Integer approved = 0;
//        Integer totalTransactions = 10;
//        BigDecimal totalDeposits  = BigDecimal.ZERO;
//        BigDecimal totalWithdrawals  = BigDecimal.valueOf(10L);
//
//        summary.setTotalApproved(approved);
//        summary.setTotalTransactions(totalTransactions);
//        summary.setTotalDeposits(totalDeposits);
//        summary.setTotalWithdrawals(totalWithdrawals);
//
//        return summary;
//    }
//    AccountSummaryRestResponse summary = new AccountSummaryRestResponse();
//
//    List<TransactionResponse> approved = transactions.stream()
//            .filter(tx -> tx.getStatus() == TransactionStatus.APPROVED)
//            .toList();
//
//        summary.setTotalTransactions(approved.size());
//
//    BigDecimal deposits = approved.stream()
//            .filter(tx -> tx.getType() == TransactionType.DEPOSIT)
//            .map(TransactionResponse::getAmount)
//            .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//    BigDecimal withdrawals = approved.stream()
//            .filter(tx -> tx.getType() == TransactionType.WITHDRAWAL)
//            .map(TransactionResponse::getAmount)
//            .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        summary.setTotalDeposits(deposits);
//        summary.setTotalWithdrawals(withdrawals);

}
