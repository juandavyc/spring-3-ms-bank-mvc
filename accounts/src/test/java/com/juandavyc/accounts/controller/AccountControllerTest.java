package com.juandavyc.accounts.controller;

import com.juandavyc.accounts.controller.helper.AccountRequestHelper;
import com.juandavyc.accounts.domian.model.enums.AccountType;
import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
/*
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    // requiere el ms de clientes arriba

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Debe realizar el ciclo de vida completo: Crear -> Buscar -> Eliminar")
    void shouldPerformFullAccountLifecycle() {
        var request = new AccountRestRequest();
        request.setClientId(UUID.fromString("a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d"));
        request.setType(AccountType.CREDIT);
        request.setOpeningBalance(new BigDecimal("500.00"));

        var createRes = AccountRequestHelper.create(restTemplate, request);
        UUID generatedId = createRes.getBody().getData().getId();

        var findRes = AccountRequestHelper.findById(restTemplate, generatedId);
        assertThat(findRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(findRes.getBody().getData().getNumber()).isNotNull();

        var deleteRes = AccountRequestHelper.delete(restTemplate, generatedId);
        assertThat(deleteRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deleteRes.getBody().getData().get("isDeleted")).isTrue();
    }


    @Test
    @DisplayName("/api/accounts/{id} - Debe retornar una cuenta existente")
    void shouldFindAccountById() {
        var accountId = UUID.fromString("d024607e-bfbb-45f7-ada8-f6c33702c9f7");
        var response = AccountRequestHelper.findById(restTemplate, accountId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getId()).isEqualTo(accountId);

    }

    @Test
    @DisplayName("/api/accounts - Debe retornar la lista de cuentas")
    void shouldFindAllAccounts() {

        var response = AccountRequestHelper.findAll(restTemplate);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData()).isInstanceOf(List.class);
        assertThat(response.getBody().getData()).isNotEmpty();

    }

    @Test
    @DisplayName("/api/accounts/{id} - Debe eliminar una cuenta")
    void shouldDeleteAccount() {
        var accountId = UUID.fromString("f3e9a2b1-2c4d-4f7a-8b1c-9d0e12345678");

        var response = AccountRequestHelper.delete(restTemplate, accountId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().get("isDeleted")).isTrue();

    }

}
*/

