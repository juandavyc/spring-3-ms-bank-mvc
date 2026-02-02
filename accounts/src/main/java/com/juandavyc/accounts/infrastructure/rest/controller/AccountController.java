package com.juandavyc.accounts.infrastructure.rest.controller;

import com.juandavyc.accounts.application.dto.account.AccountCommand;
import com.juandavyc.accounts.application.dto.account.AccountResponse;
import com.juandavyc.accounts.application.usecases.AccountUseCase;

import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestRequest;
import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestResponse;
import com.juandavyc.accounts.infrastructure.rest.mapper.AccountRestMapper;
import com.juandavyc.core.dto.response.ResponseDto;
import com.juandavyc.core.mapper.ResponseMapper;
import com.juandavyc.core.shared.AccountResponseCode;
import com.juandavyc.core.shared.ClientResponseCode;
import com.juandavyc.core.validation.ValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountUseCase service;
    private final AccountRestMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<AccountRestResponse>> create(
            @Validated(ValidationGroup.OnCreate.class)
            @RequestBody AccountRestRequest request
    ) {

        AccountCommand command = mapper.toCommand(request);
        AccountResponse accountApplication = service.create(command);
        AccountRestResponse accountResponse = mapper.toRestResponse(accountApplication);

        return ResponseEntity
                .status(AccountResponseCode.CREATED.getValue())
                .body(ResponseMapper.response(AccountResponseCode.CREATED, accountResponse));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<AccountRestResponse>>> findAll() {

        List<AccountResponse> accountResponses = service.findAll();

        List<AccountRestResponse> responsesDto = accountResponses.stream()
                .map(mapper::toRestResponse)
                .toList();

        return ResponseEntity
                .status(AccountResponseCode.SUCCESS.getValue())
                .body(ResponseMapper.response(AccountResponseCode.SUCCESS, responsesDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<AccountRestResponse>> findById(
            @PathVariable UUID id
    ) {
        AccountResponse accountResponse = service.findById(id);
        AccountRestResponse accountRestResponse = mapper.toRestResponse(accountResponse);
        return ResponseEntity
                .status(AccountResponseCode.SUCCESS.getValue())
                .body(ResponseMapper.response(AccountResponseCode.SUCCESS, accountRestResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Map<String, Boolean>>> delete(
            @PathVariable UUID id
    ){
        service.delete(id);
        return ResponseEntity
                .status(AccountResponseCode.DELETED.getValue())
                .body(ResponseMapper.response(AccountResponseCode.DELETED, Map.of("isDeleted", true)));

    }

}
