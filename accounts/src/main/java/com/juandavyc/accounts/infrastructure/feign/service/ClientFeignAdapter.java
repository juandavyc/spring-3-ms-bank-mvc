package com.juandavyc.accounts.infrastructure.feign.service;

import com.juandavyc.accounts.application.usecases.ClientPort;
import com.juandavyc.accounts.infrastructure.feign.ClientFeign;
import com.juandavyc.accounts.infrastructure.feign.dto.ClientRestResponse;
import com.juandavyc.accounts.infrastructure.feign.mapper.ClientRestMapper;
import com.juandavyc.core.dto.application.ClientResponse;
import com.juandavyc.core.dto.response.ResponseDto;
import com.juandavyc.core.exceptions.ClientServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientFeignAdapter implements ClientPort {

    private final ClientFeign feignClient;
    private final ClientRestMapper mapper;

    @Cacheable(value = "clients", key = "#id")
    @Retry(name = "clientCriticalRetry", fallbackMethod = "fallbackGetClient")
    public ClientResponse getClientById(UUID id) {
        log.info("Getting Feign client by id {}", id);
        ResponseDto<ClientRestResponse> client = feignClient.getById(id);
        return mapper.toDomain(client.getData());
    }

    @Cacheable(value = "clients", key = "#id")
    @CircuitBreaker(name = "clientService", fallbackMethod = "fallbackClientForReport")
    public ClientResponse getClientByIdForReport(UUID id) {
        ResponseDto<ClientRestResponse> client = feignClient.getById(id);
        return mapper.toDomain(client.getData());
    }

    public ClientResponse fallbackGetClient(UUID id, Exception e) {
        throw new ClientServiceUnavailableException(e.getMessage());
    }

    public ClientResponse fallbackClientForReport(UUID id, Exception ex) {
        ClientResponse client = new ClientResponse();
        client.setId(id);
        client.setFullName("-- --");
        client.setIdentification(" -- --");
        return client;
    }

}
