package com.juandavyc.accounts.application.dto.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientSummaryResponse {

    private UUID id;
    private String fullName;
    private String identification;

}
