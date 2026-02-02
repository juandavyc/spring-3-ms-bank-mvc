package com.juandavyc.accounts.service;


import com.juandavyc.accounts.application.dto.account.AccountCommand;
import com.juandavyc.accounts.application.dto.account.AccountResponse;
import com.juandavyc.accounts.application.mapper.AccountApplicationMapper;
import com.juandavyc.accounts.application.service.AccountServiceImpl;
import com.juandavyc.accounts.application.usecases.AccountUseCase;
import com.juandavyc.accounts.application.usecases.ClientPort;
import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import com.juandavyc.accounts.domian.port.AccountPort;
import com.juandavyc.accounts.infrastructure.persistence.adapter.AccountRepositoryAdapter;
import com.juandavyc.core.exceptions.AccountTypeAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    AccountServiceImpl underTest;

    @Mock
    private AccountPort accountPort;

    @Mock
    private AccountApplicationMapper accountMapper;

    @Mock
    private ClientPort clientPort;

    @Captor
    ArgumentCaptor<Account> accountCaptor;


    @BeforeEach
    void setUp() {
        underTest = new AccountServiceImpl(
                accountPort,
                accountMapper,
                clientPort
        );
    }

    @Test
    @DisplayName("Debe crear una cuenta exitosamente cuando el cliente existe y no tiene cuenta de ese tipo")
    void create_ShouldCreateAccountSuccessfully() {
        // 1. Arrange
        UUID clientId = UUID.randomUUID();
        AccountCommand command = new AccountCommand();
        command.setClientId(clientId);
        command.setType(AccountType.CREDIT);
        command.setOpeningBalance(new BigDecimal("100.00"));

        Account domainAccount = new Account();
        domainAccount.setClientId(clientId);
        domainAccount.setType(AccountType.CREDIT);

        Account savedAccount = new Account();
        savedAccount.setId(UUID.randomUUID());

        AccountResponse expectedResponse = new AccountResponse();
        expectedResponse.setId(savedAccount.getId());

        // BDD Style Mocks
        when(clientPort.getClientById(clientId)).thenReturn(null);
        when(accountPort.existsByClientIdAndType(clientId, command.getType())).thenReturn(false);
        when(accountMapper.toDomain(command)).thenReturn(domainAccount);
        when(accountPort.save(any(Account.class))).thenReturn(savedAccount);
        when(accountMapper.toResponse(savedAccount)).thenReturn(expectedResponse);

        AccountResponse result = underTest.create(command);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(expectedResponse.getId());

        verify(clientPort).getClientById(clientId);
        verify(accountPort).existsByClientIdAndType(clientId, command.getType());

        verify(accountPort).save(accountCaptor.capture());
        Account capturedAccount = accountCaptor.getValue();

        assertThat(capturedAccount.getClientId()).isEqualTo(clientId);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el cliente ya tiene una cuenta del mismo tipo")
    void create_ShouldThrowException_WhenTypeAlreadyExists() {
        UUID clientId = UUID.randomUUID();
        AccountCommand command = new AccountCommand();
        command.setClientId(clientId);
        command.setType(AccountType.CREDIT);

        when(accountPort.existsByClientIdAndType(clientId, AccountType.CREDIT)).thenReturn(true);

        assertThatThrownBy(() -> underTest.create(command))
                .isInstanceOf(AccountTypeAlreadyExistsException.class)
                .hasMessageContaining("CREDIT");

        verify(accountPort, never()).save(any());
    }

}
