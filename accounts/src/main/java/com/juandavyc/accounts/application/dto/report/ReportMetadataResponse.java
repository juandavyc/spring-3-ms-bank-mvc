package com.juandavyc.accounts.application.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReportMetadataResponse {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime generatedAt;

}
