package com.juandavyc.accounts.controller.helper;

import com.juandavyc.accounts.domian.model.enums.AccountType;
import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestRequest;
import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestResponse;
import com.juandavyc.core.dto.response.ResponseDto;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AccountRequestHelper {

    public static ResponseEntity<ResponseDto<AccountRestResponse>> create(
            TestRestTemplate testRestTemplate,
            AccountRestRequest request
    ) {
        ParameterizedTypeReference<ResponseDto<AccountRestResponse>> responseType =
                new ParameterizedTypeReference<ResponseDto<AccountRestResponse>>() {};

        return testRestTemplate.exchange(
                "/api/accounts",
                HttpMethod.POST,
                new HttpEntity<>(request),
                responseType
        );
    }

    public static ResponseEntity<ResponseDto<AccountRestResponse>> findById(
            TestRestTemplate restTemplate,
            UUID id
    ) {
        return restTemplate.exchange(
                "/api/accounts/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseDto<AccountRestResponse>>() {}
        );
    }

    public static ResponseEntity<ResponseDto<Map<String, Boolean>>> delete(
            TestRestTemplate restTemplate,
            UUID id
    ) {
        return restTemplate.exchange(
                "/api/accounts/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ResponseDto<Map<String, Boolean>>>() {}
        );
    }

    public static  ResponseEntity<ResponseDto<List<AccountRestResponse>>> findAll(
            TestRestTemplate restTemplate
    ){
        return restTemplate.exchange(
                "/api/accounts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseDto<List<AccountRestResponse>>>() {}
        );
    }



}
