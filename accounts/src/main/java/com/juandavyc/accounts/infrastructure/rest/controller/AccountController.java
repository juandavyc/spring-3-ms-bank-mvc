package com.juandavyc.accounts.infrastructure.rest.controller;

import com.juandavyc.accounts.application.dto.AccountCommand;
import com.juandavyc.accounts.application.dto.AccountResponse;
import com.juandavyc.accounts.application.usecases.AccountUseCase;

import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestRequest;
import com.juandavyc.accounts.infrastructure.rest.dto.AccountRestResponse;
import com.juandavyc.accounts.infrastructure.rest.mapper.AccountRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountUseCase service;
    private final AccountRestMapper mapper;

    @PostMapping
    public ResponseEntity<AccountRestResponse> createAccount(
            @RequestBody AccountRestRequest request
    ) {

        AccountCommand command = mapper.toCommand(request);
        AccountResponse accountApplication = service.create(command);
        AccountRestResponse accountResponse = mapper.toRestResponse(accountApplication);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountResponse);
    }

    @GetMapping
    public ResponseEntity<List<AccountRestResponse>> search() {

        List<AccountResponse> accountResponses = service.search();

        List<AccountRestResponse> responsesDto = accountResponses.stream()
                .map(mapper::toRestResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responsesDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable UUID id,
            @RequestBody AccountRestRequest request
    ){

        AccountCommand command = mapper.toCommand(request);
        AccountResponse accountResponse = service.update(id, command);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(accountResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRestResponse> getAccount(
            @PathVariable UUID id
    ) {
        AccountResponse accountResponse = service.searchById(id);

        AccountRestResponse accountRestResponse = mapper.toRestResponse(accountResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountRestResponse);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable UUID id
    ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
