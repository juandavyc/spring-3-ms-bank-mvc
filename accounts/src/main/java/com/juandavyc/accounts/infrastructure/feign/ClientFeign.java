package com.juandavyc.accounts.infrastructure.feign;


import com.juandavyc.accounts.infrastructure.feign.dto.ClientRestResponse;
import com.juandavyc.core.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "clients", url = "${external-services.clients-url}")

public interface ClientFeign {

    @GetMapping("/api/clients/{id}")
    ResponseDto<ClientRestResponse> getById(@PathVariable("id") UUID id);


}
