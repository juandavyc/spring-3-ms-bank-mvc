package com.juandavyc.clients.controller.helper;

import com.juandavyc.core.dto.response.ResponseDto;
import com.juandavyc.clients.infrastructure.rest.dto.ClientRestRequest;
import com.juandavyc.clients.infrastructure.rest.dto.ClientRestResponse;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class ClientRequestHelper {
    public static ResponseEntity<ResponseDto<ClientRestResponse>> create(TestRestTemplate restTemplate, ClientRestRequest request) {
        return restTemplate.exchange("/api/clients", HttpMethod.POST, new HttpEntity<>(request),
                new ParameterizedTypeReference<ResponseDto<ClientRestResponse>>() {});
    }

    public static ResponseEntity<ResponseDto<ClientRestResponse>> findById(TestRestTemplate restTemplate, UUID id) {
        return restTemplate.exchange("/api/clients/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseDto<ClientRestResponse>>() {});
    }
}
