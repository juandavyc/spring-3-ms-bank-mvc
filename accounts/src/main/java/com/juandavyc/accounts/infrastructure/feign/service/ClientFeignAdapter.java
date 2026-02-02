package com.juandavyc.accounts.infrastructure.feign.service;

import com.juandavyc.accounts.application.dto.ClientResponse;
import com.juandavyc.accounts.application.usecases.ClientPort;
import com.juandavyc.accounts.infrastructure.feign.ClientFeign;
import com.juandavyc.accounts.infrastructure.feign.dto.ClientRestResponse;
import com.juandavyc.accounts.infrastructure.feign.mapper.ClientRestMapper;
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

    // sistemas criticos, crear cuenta
    @Cacheable(value = "clients", key = "#id")
    @Retry(name = "clientCriticalRetry", fallbackMethod = "fallbackGetClient")
    public ClientResponse getClientById(UUID id) {
        log.info("Getting Feign client by id {}", id);
        ClientRestResponse client = feignClient.getById(id);
        return mapper.toDomain(client);
    }

    // reportes
    //@Retry(name = "clientServiceRetry")
    @Cacheable(value = "clients", key = "#id")
    @CircuitBreaker(name = "clientService", fallbackMethod = "fallbackClientForReport")
    public ClientResponse getClientByIdForReport(UUID id) {
        ClientRestResponse client = feignClient.getById(id);
        return mapper.toDomain(client);
    }

    public ClientResponse fallbackGetClient(UUID id, Exception e) {
        log.error("Todos los reintentos fallaron para el id {}. Error: {}", id, e.getMessage());

        // Aquí lanzas tu excepción personalizada
        //throw new ClientNotFoundException("No se pudo recuperar el cliente tras varios intentos.");
        throw new RuntimeException("No se pudo recuperar el cliente tras varios intentos.");
    }

    public ClientResponse fallbackClientForReport(UUID id, Exception ex) {
        log.warn("Client service unavailable, report degraded");
        ClientResponse client = new ClientResponse();
        client.setId(id);
        client.setFullName("-- --");
        return client;
    }
}
