package com.juandavyc.accounts.infrastructure.persistence.entity;


import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private BigDecimal openingBalance;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private UUID clientId;

}
