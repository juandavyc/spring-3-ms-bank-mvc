package com.juandavyc.accounts.application.usecases;

import com.juandavyc.accounts.application.dto.report.ReportResponse;

import java.time.LocalDate;
import java.util.UUID;

public interface ReportUseCase {

    ReportResponse execute(LocalDate startDate, LocalDate endDate, UUID clientId);

}
