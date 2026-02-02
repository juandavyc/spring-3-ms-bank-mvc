package com.juandavyc.accounts.application.dto.account;
import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AccountCommand {

    private String number;
    private BigDecimal openingBalance;
    private AccountStatus status;
    private AccountType type;
    private UUID clientId;

}
