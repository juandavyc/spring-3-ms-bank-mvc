package com.juandavyc.accounts.infrastructure.feign;


import com.juandavyc.accounts.infrastructure.feign.dto.ClientRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "clients", url = "http://localhost:8082")
public interface ClientFeign {


    @GetMapping("/api/clients/{id}")
    ClientRestResponse getById(@PathVariable("id") UUID id);


}
