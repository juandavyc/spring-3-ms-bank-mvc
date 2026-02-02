package com.juandavyc.accounts.infrastructure.feign.service;

import com.juandavyc.accounts.application.dto.ClientResponse;
import com.juandavyc.accounts.application.usecases.ClientPort;
import com.juandavyc.accounts.infrastructure.feign.ClientFeign;
import com.juandavyc.accounts.infrastructure.feign.dto.ClientRestResponse;
import com.juandavyc.accounts.infrastructure.feign.mapper.ClientRestMapper;
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
    public ClientResponse getClientById(UUID id) {
        log.info("Getting Feign client by id {}", id);
        ClientRestResponse client = feignClient.getById(id);
        return mapper.toDomain(client);
    }


}
