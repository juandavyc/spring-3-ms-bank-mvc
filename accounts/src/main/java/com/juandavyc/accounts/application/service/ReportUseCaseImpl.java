package com.juandavyc.accounts.application.service;

import com.juandavyc.accounts.application.dto.report.AccountSummaryResponse;
import com.juandavyc.accounts.application.dto.report.AccountTransactionsResponse;
import com.juandavyc.accounts.application.dto.report.ReportMetadataResponse;
import com.juandavyc.accounts.application.dto.report.ReportResponse;
import com.juandavyc.accounts.application.dto.report.ReportSummaryResponse;
import com.juandavyc.accounts.application.dto.transaction.TransactionResponse;
import com.juandavyc.accounts.application.mapper.ClientApplicationMapper;
import com.juandavyc.accounts.application.mapper.TransactionApplicationMapper;
import com.juandavyc.accounts.application.usecases.ClientPort;
import com.juandavyc.accounts.application.usecases.ReportUseCase;
import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.Transaction;
import com.juandavyc.accounts.domian.model.enums.TransactionStatus;
import com.juandavyc.accounts.domian.model.enums.TransactionType;
import com.juandavyc.accounts.domian.port.AccountPort;
import com.juandavyc.accounts.domian.port.TransactionPort;
import com.juandavyc.core.dto.application.ClientResponse;
import com.juandavyc.core.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ReportUseCaseImpl implements ReportUseCase {

    private final ClientPort clientPort;
    private final AccountPort accountPort;
    private final TransactionPort transactionPort;

    private final ClientApplicationMapper clientMapper;
    private final TransactionApplicationMapper transactionMapper;

    @Override
    public ReportResponse execute(LocalDate startDate, LocalDate endDate, UUID clientId) {

        ClientResponse client = clientPort.getClientByIdForReport(clientId);

        if (Objects.isNull(client)) {
            throw new ResourceNotFoundException("Client", "id", clientId);
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        ReportResponse reportResponse = new ReportResponse();
        ReportMetadataResponse metadata = reportMetadata(startDateTime, endDateTime);

        ReportSummaryResponse summary = new ReportSummaryResponse();

        List<Account> accounts = accountPort.findByClientId(clientId);

        summary.setTotalAccounts(accounts.size());

        List<AccountTransactionsResponse> accountTransactionResponses = accounts.stream()
                .map(account -> {

                    AccountTransactionsResponse atr =
                            processAccountForReport(account, startDateTime, endDateTime);

                    int total = summary.getTotalTransactions() + atr.getSummary().getTotalTransactions();
                    int totalApproved = summary.getTotalApprovedTransactions() + atr.getSummary().getTotalApproved();
                    int totalRejected = summary.getTotalRejectedTransactions() + atr.getSummary().getTotalRejected();

                    summary.setTotalTransactions(total);
                    summary.setTotalApprovedTransactions(totalApproved);
                    summary.setTotalRejectedTransactions(totalRejected);

                    return atr;
                })
                .toList();


        reportResponse.setMetadata(metadata);
        reportResponse.setClient(clientMapper.toResponse(client));
        reportResponse.setAccounts(accountTransactionResponses);
        reportResponse.setSummary(summary);


        return reportResponse;
    }

    private ReportMetadataResponse reportMetadata(LocalDateTime startDate, LocalDateTime endDate) {
        return new ReportMetadataResponse(startDate, endDate, LocalDateTime.now());
    }

    private AccountTransactionsResponse processAccountForReport(
            Account account, LocalDateTime startDate, LocalDateTime endDate) {

        List<Transaction> transactions = transactionPort
                .findByAccountIdAndTimestampBetween(account.getId(), startDate, endDate);

        List<TransactionResponse> transactionResponses =
                transactionMapper.toResponseList(transactions);

        AccountSummaryResponse summaryResponse = calculateAccountMetrics(transactions);

        return AccountTransactionsResponse.builder()
                .id(account.getId())
                .number(account.getNumber())
                .type(account.getType())
                .status(account.getStatus())
                .balance(account.getBalance())
                .transactions(transactionResponses)
                .summary(summaryResponse)
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


}
