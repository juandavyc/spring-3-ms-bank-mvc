package com.juandavyc.accounts.adapter;


import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.enums.AccountStatus;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import com.juandavyc.accounts.infrastructure.persistence.adapter.AccountRepositoryAdapter;
import com.juandavyc.accounts.infrastructure.persistence.entity.AccountEntity;
import com.juandavyc.accounts.infrastructure.persistence.mapper.AccountPersistenceMapper;
import com.juandavyc.accounts.infrastructure.persistence.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountAdapterTest {

    @Autowired
    private AccountRepositoryAdapter underTest;

    @Autowired
    private AccountRepository  repository;

    @Autowired
    private AccountPersistenceMapper mapper;

    @BeforeEach
    void setUp() {
        underTest = new AccountRepositoryAdapter(repository, mapper);
    }

    @Transactional
    @Test
    @DisplayName("Debe retornar una cuenta nueva")
    void save_shouldPersistAccount() {
        Account newAccount = new Account();

        newAccount.setNumber("123456");
        newAccount.setOpeningBalance(new BigDecimal("1000.00"));
        newAccount.setBalance(new BigDecimal("1000.00"));
        newAccount.setStatus(AccountStatus.ACTIVE);
        newAccount.setType(AccountType.CREDIT);
        newAccount.setClientId(UUID.randomUUID());

        Account savedAccount = underTest.save(newAccount);

        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isNotNull();
        assertThat(savedAccount.getNumber()).isEqualTo(newAccount.getNumber());

        assertThat(repository.findById(savedAccount.getId()))
                .isPresent()
                .get()
                .satisfies(entity -> {
                    assertThat(entity.getBalance()).isEqualByComparingTo(new BigDecimal("1000.00"));
                    assertThat(entity.getClientId()).isEqualTo(newAccount.getClientId());
                    assertThat(entity.getStatus()).isEqualTo(AccountStatus.ACTIVE);
                });


    }
    @Transactional
    @Test
    @DisplayName("Debe retornar la cuenta cuando existe una cuenta activa en la BD")
    void findById_ShouldReturnAccount_WhenActiveAccountExists() {

        AccountEntity activeEntity = new AccountEntity();
        activeEntity.setNumber("478512");
        activeEntity.setOpeningBalance(new BigDecimal("2000.00"));
        activeEntity.setBalance(new BigDecimal("2000.00"));
        activeEntity.setStatus(AccountStatus.ACTIVE);
        activeEntity.setType(AccountType.CREDIT);
        activeEntity.setClientId(UUID.randomUUID());

        activeEntity = repository.save(activeEntity);

        Optional<Account> result = underTest.findById(activeEntity.getId());

        assertThat(result)
                .isPresent()
                .get()
                .satisfies(account -> {
                    assertThat(account.getNumber()).isEqualTo("478512");
                    assertThat(account.getStatus()).isEqualTo(AccountStatus.ACTIVE);
                    assertThat(account.getType()).isEqualTo(AccountType.CREDIT);
                    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal("2000.00"));
                    assertThat(account.getClientId()).isNotNull();
                });
    }


    @Test
    @DisplayName("deleteById debe realizar un borrado lógico (cambiar estado a INACTIVE)")
    void deleteById_ShouldPerformSoftDelete() {
        AccountEntity entity = new AccountEntity(null, "999", BigDecimal.ZERO, BigDecimal.ZERO,
                AccountStatus.ACTIVE, AccountType.CREDIT, UUID.randomUUID());

        entity = repository.save(entity);
        UUID id = entity.getId();

        // Act
        underTest.deleteById(id);

        // Assert
        Optional<AccountEntity> deletedEntity = repository.findById(id);
        assertThat(deletedEntity).isPresent();
        assertThat(deletedEntity.get().getStatus()).isEqualTo(AccountStatus.INACTIVE);
    }

}
