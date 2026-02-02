package com.juandavyc.accounts.application.usecases;

import com.juandavyc.accounts.application.dto.AccountSummaryResponse;
import com.juandavyc.accounts.application.dto.ReportResponse;
import com.juandavyc.accounts.domian.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReportUseCase {

    //AccountSummaryResponse execute(List<Transaction> transactions);

    ReportResponse execute(LocalDate startDate, LocalDate endDate, UUID clientId);


}
