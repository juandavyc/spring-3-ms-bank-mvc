package com.juandavyc.accounts.infrastructure.rest.dto;

import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRestRequest {

    private String number;
    private BigDecimal openingBalance;
    private BigDecimal balance;

    private AccountType type;
    private UUID clientId;

}
