package com.juandavyc.accounts.infrastructure.rest.controller;

import com.juandavyc.accounts.application.dto.TransactionCommand;
import com.juandavyc.accounts.application.dto.TransactionResponse;
import com.juandavyc.accounts.application.usecases.TransactionUseCase;
import com.juandavyc.accounts.infrastructure.rest.dto.TransactionRestRequest;
import com.juandavyc.accounts.infrastructure.rest.dto.TransactionRestResponse;
import com.juandavyc.accounts.infrastructure.rest.mapper.TransactionRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionUseCase service;
    private final TransactionRestMapper mapper;

    @PostMapping
    public ResponseEntity<TransactionRestResponse> create(
            @RequestBody TransactionRestRequest request
    ) {

        TransactionCommand command = mapper.toCommand(request);
        TransactionResponse transactionApplication = service.create(command);
        TransactionRestResponse response = mapper.toRestResponse(transactionApplication);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionRestResponse>> search(){

        List<TransactionResponse> transactions = service.search();

        List<TransactionRestResponse> responsesDto = transactions.stream()
                .map(mapper::toRestResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responsesDto);
    }


}
