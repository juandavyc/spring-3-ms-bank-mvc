package com.juandavyc.accounts.application.dto;


import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountResponse {

    private UUID id;
    private String number;
    private BigDecimal openingBalance;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;
    private UUID clientId;

}
