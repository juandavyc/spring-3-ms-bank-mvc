package com.juandavyc.accounts.application.dto.transaction;

import com.juandavyc.accounts.domian.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionCommand {

    private UUID accountId;
    private TransactionType type;
    private BigDecimal amount;

}
