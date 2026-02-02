package com.juandavyc.accounts.infrastructure.rest.controller;

import com.juandavyc.accounts.application.dto.report.ReportResponse;
import com.juandavyc.accounts.application.usecases.ReportUseCase;
import com.juandavyc.accounts.infrastructure.rest.dto.ReportRestResponse;
import com.juandavyc.accounts.infrastructure.rest.mapper.ReportRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportUseCase service;
    private final ReportRestMapper mapper;

    @GetMapping
    public ResponseEntity<ReportRestResponse> generateReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam UUID clientId
    ) {

        ReportResponse response = service.execute(startDate,endDate,clientId);
        ReportRestResponse reportRestResponse = mapper.toResponse(response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reportRestResponse);
    }

}
