package com.juandayvc.clients.infrastructure.rest.controller;

import com.juandayvc.clients.application.dto.ClientCommand;
import com.juandayvc.clients.application.dto.ClientResponse;
import com.juandayvc.clients.application.usecases.ClientUseCase;
import com.juandayvc.clients.infrastructure.rest.dto.ClientRestRequest;
import com.juandayvc.clients.infrastructure.rest.dto.ClientRestResponse;
import com.juandayvc.clients.infrastructure.rest.mapper.ClientRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientUseCase service;
    private final ClientRestMapper mapper;

    @PostMapping
    public ResponseEntity<ClientRestResponse> create(
            @RequestBody ClientRestRequest request
    ) {

        ClientCommand command = mapper.toCommand(request);
        ClientResponse response = service.create(command);
        ClientRestResponse accountResponse = mapper.toRestResponse(response);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountResponse);

    }

    @GetMapping
    public ResponseEntity<List<ClientRestResponse>> findAll() {

        List<ClientResponse> clientResponses = service.findAll();

        List<ClientRestResponse> responsesDto = clientResponses.stream()
                .map(mapper::toRestResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responsesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientRestResponse> findById(
            @PathVariable UUID id
    ) {

        ClientResponse clientResponse = service.findById(id);

        ClientRestResponse clientRestResponse = mapper.toRestResponse(clientResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientRestResponse);
    }

}
