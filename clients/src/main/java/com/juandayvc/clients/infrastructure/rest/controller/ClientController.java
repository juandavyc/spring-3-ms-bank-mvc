package com.juandayvc.clients.infrastructure.rest.controller;

import com.juandavyc.core.dto.response.ResponseDto;
import com.juandavyc.core.mapper.ResponseMapper;
import com.juandavyc.core.shared.ClientResponseCode;
import com.juandavyc.core.validation.ValidationGroup;
import com.juandayvc.clients.application.dto.ClientCommand;
import com.juandayvc.clients.application.dto.ClientResponse;
import com.juandayvc.clients.application.usecases.ClientUseCase;
import com.juandayvc.clients.infrastructure.rest.dto.ClientRestRequest;
import com.juandayvc.clients.infrastructure.rest.dto.ClientRestResponse;
import com.juandayvc.clients.infrastructure.rest.mapper.ClientRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientUseCase service;
    private final ClientRestMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<ClientRestResponse>> create(
            @Validated(ValidationGroup.OnCreate.class)
            @RequestBody ClientRestRequest request
    ) {

        ClientCommand command = mapper.toCommand(request);
        ClientResponse response = service.create(command);
        ClientRestResponse accountResponse = mapper.toRestResponse(response);

        return ResponseEntity
                .status(ClientResponseCode.CREATED.getValue())
                .body(ResponseMapper.response(ClientResponseCode.CREATED, accountResponse));

    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ClientRestResponse>>> findAll() {

        List<ClientResponse> clientResponses = service.findAll();

        List<ClientRestResponse> responsesDto = mapper.toRestResponseList(clientResponses);

        return ResponseEntity
                .status(ClientResponseCode.SUCCESS.getValue())
                .body(ResponseMapper.response(ClientResponseCode.SUCCESS, responsesDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ClientRestResponse>> findById(
            @PathVariable UUID id
    ) {

        ClientResponse clientResponse = service.findById(id);
        ClientRestResponse clientRestResponse = mapper.toRestResponse(clientResponse);

        return ResponseEntity
                .status(ClientResponseCode.SUCCESS.getValue())
                .body(ResponseMapper.response(ClientResponseCode.SUCCESS, clientRestResponse));

    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ClientRestResponse>> delete(
            @PathVariable UUID id,
            @Validated(ValidationGroup.OnUpdate.class)
            @RequestBody ClientRestRequest request
    ) {

        ClientCommand command = mapper.toCommand(request);
        ClientResponse accountResponse = service.update(id, command);
        ClientRestResponse clientRestResponse = mapper.toRestResponse(accountResponse);

        return ResponseEntity
                .status(ClientResponseCode.SUCCESS.getValue())
                .body(ResponseMapper.response(ClientResponseCode.SUCCESS, clientRestResponse));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Map<String, Boolean>>> delete(
            @PathVariable UUID id
    ) {

        service.deleteById(id);

        return ResponseEntity
                .status(ClientResponseCode.SUCCESS.getValue())
                .body(ResponseMapper.response(ClientResponseCode.SUCCESS, Map.of("isDeleted", true)));

    }
}
