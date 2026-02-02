package com.juandayvc.clients.controller;


import com.juandavyc.core.dto.response.ResponseDto;
import com.juandayvc.clients.controller.helper.ClientRequestHelper;
import com.juandayvc.clients.domain.model.enums.GenderType;
import com.juandayvc.clients.infrastructure.rest.dto.ClientRestRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Debe crear, buscar un cliente exitosamente")
    void clientLifecycleTest() {

        ClientRestRequest request = new ClientRestRequest();
        request.setFullName("Juan Perez");
        request.setIdentification("1712345678");
        request.setAddress("Quito, Ecuador");
        request.setPhoneNumber("12345343");
        request.setPassword("securePass123");
        request.setAge(30);
        request.setGender(GenderType.MALE);

        var createRes = ClientRequestHelper.create(restTemplate, request);
        assertThat(createRes.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        UUID clientId = createRes.getBody().getData().getId();

        var findRes = ClientRequestHelper.findById(restTemplate, clientId);
        assertThat(findRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(findRes.getBody().getData().getFullName()).isEqualTo("Juan Perez");

    }


    @Test
    @DisplayName("/api/clients/{id} - Debe retornar el cliente cuando existe")
    void shouldFindClientById() {

        ClientRestRequest request = new ClientRestRequest();
        request.setFullName("Juan Perez");
        request.setIdentification("1712345678");
        request.setAddress("Quito, Ecuador");
        request.setPhoneNumber("12345343");
        request.setPassword("securePass123");
        request.setAge(30);
        request.setGender(GenderType.MALE);

        var created = ClientRequestHelper.create(restTemplate, request);

        UUID id = created.getBody().getData().getId();

        var response = ClientRequestHelper.findById(restTemplate, id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getId()).isEqualTo(id);
        assertThat(response.getBody().getData().getIdentification()).isEqualTo("1712345678");
    }


    @Test
    @DisplayName("DELETE /api/clients/{id} - Debe retornar éxito y confirmar el borrado lógico")
    void shouldDeleteClientSuccessfully() {

        ClientRestRequest request = new ClientRestRequest();
        request.setFullName("Juan Perez");
        request.setIdentification("1712345678");
        request.setAddress("Quito, Ecuador");
        request.setPhoneNumber("12345343");
        request.setPassword("securePass123");
        request.setAge(30);
        request.setGender(GenderType.MALE);

        var createdRes = ClientRequestHelper.create(restTemplate, request);
        UUID idToDelete = createdRes.getBody().getData().getId();


        ResponseEntity<ResponseDto<Map<String, Boolean>>> response = restTemplate.exchange(
                "/api/clients/" + idToDelete,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<ResponseDto<Map<String, Boolean>>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData().get("isDeleted")).isTrue();

    }
}
