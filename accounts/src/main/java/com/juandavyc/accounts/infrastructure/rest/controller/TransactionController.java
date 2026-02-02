package com.juandavyc.accounts.infrastructure.rest.controller;

import com.juandavyc.accounts.application.dto.transaction.TransactionCommand;
import com.juandavyc.accounts.application.dto.transaction.TransactionResponse;
import com.juandavyc.accounts.application.usecases.TransactionUseCase;
import com.juandavyc.accounts.domian.model.enums.TransactionStatus;
import com.juandavyc.accounts.infrastructure.rest.dto.TransactionRestRequest;
import com.juandavyc.accounts.infrastructure.rest.dto.TransactionRestResponse;
import com.juandavyc.accounts.infrastructure.rest.mapper.TransactionRestMapper;
import com.juandavyc.core.dto.response.ResponseDto;
import com.juandavyc.core.mapper.ResponseMapper;
import com.juandavyc.core.shared.TransactionResponseCode;
import com.juandavyc.core.validation.ValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Validated
public class TransactionController {

    private final TransactionUseCase service;
    private final TransactionRestMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<TransactionRestResponse>> create(
            @Validated(ValidationGroup.OnCreate.class)
            @RequestBody TransactionRestRequest request
    ) {

        TransactionCommand command = mapper.toCommand(request);
        TransactionResponse transactionApplication = service.create(command);
        TransactionRestResponse response = mapper.toRestResponse(transactionApplication);

        if (response.getStatus().equals(TransactionStatus.APPROVED)) {
            return ResponseEntity
                    .status(TransactionResponseCode.CREATED.getValue())
                    .body(ResponseMapper.response(TransactionResponseCode.CREATED, response));
        }

        return ResponseEntity
                .status(TransactionResponseCode.REJECTED.getValue())
                .body(ResponseMapper.response(TransactionResponseCode.REJECTED, response));


    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<TransactionRestResponse>>> findAll() {

        List<TransactionResponse> transactions = service.findAll();

        List<TransactionRestResponse> responsesDto = transactions.stream()
                .map(mapper::toRestResponse)
                .toList();

        return ResponseEntity
                .status(TransactionResponseCode.SUCCESS.getValue())
                .body(ResponseMapper.response(TransactionResponseCode.SUCCESS, responsesDto));

    }


}
