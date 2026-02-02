package com.juandayvc.clients.adapter;


import com.juandayvc.clients.application.service.ClientServiceImpl;
import com.juandayvc.clients.domain.model.Client;
import com.juandayvc.clients.domain.model.enums.ClientStatus;
import com.juandayvc.clients.domain.model.enums.GenderType;
import com.juandayvc.clients.infrastructure.persistence.adapter.ClientRepositoryAdapter;
import com.juandayvc.clients.infrastructure.persistence.entity.ClientEntity;
import com.juandayvc.clients.infrastructure.persistence.mapper.ClientPersistenceMapper;
import com.juandayvc.clients.infrastructure.persistence.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientAdapterTest {

    @Autowired
    private ClientRepositoryAdapter underTest;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientPersistenceMapper mapper;

    @Transactional
    @Test
    @DisplayName("Debe persistir un cliente nuevo y mapear los campos de Person")
    void save_shouldPersistClient() {
        Client newClient = new Client(
                null,
                "Jose Lema",
                GenderType.MALE,
                30,
                "1712345678",
                "Otavalo sn y principal",
                "098254785",
                "1234",
                ClientStatus.ACTIVE
        );

        Client savedClient = underTest.save(newClient);

        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getId()).isNotNull();
        assertThat(savedClient.getFullName()).isEqualTo("Jose Lema");

        assertThat(repository.findById(savedClient.getId()))
                .isPresent()
                .get()
                .satisfies(entity -> {
                    assertThat(entity.getIdentification()).isEqualTo("1712345678");
                    assertThat(entity.getPassword()).isEqualTo("1234");
                    assertThat(entity.getStatus()).isEqualTo(ClientStatus.ACTIVE);
                    assertThat(entity.getAge()).isEqualTo(30);
                });
    }

    @Transactional
    @Test
    @DisplayName("Debe retornar el cliente cuando existe en la BD")
    void findById_ShouldReturnClient_WhenExists() {

        ClientEntity entity = new ClientEntity();
        entity.setFullName("Marianela Montalvo");
        entity.setIdentification("1722222222");
        entity.setAge(28);
        entity.setGender(GenderType.FEMALE);
        entity.setAddress("Amazonas y NNUU");
        entity.setPhoneNumber("0988888888");
        entity.setPassword("5678");
        entity.setStatus(ClientStatus.ACTIVE);

        entity = repository.save(entity);

        Optional<Client> result = underTest.findById(entity.getId());

        assertThat(result)
                .isPresent()
                .get()
                .satisfies(client -> {
                    assertThat(client.getFullName()).isEqualTo("Marianela Montalvo");
                    assertThat(client.getIdentification()).isEqualTo("1722222222");
                    assertThat(client.getStatus()).isEqualTo(ClientStatus.ACTIVE);
                });
    }

   @Transactional
    @Test
    @DisplayName("deleteById debe realizar un borrado lógico del cliente")
    void deleteById_ShouldPerformSoftDelete() {

        ClientEntity entity = new ClientEntity();
        entity.setFullName("Borrar Me");
        entity.setIdentification("999999999");
        entity.setStatus(ClientStatus.ACTIVE);
        entity = repository.save(entity);

        UUID id = entity.getId();

        underTest.deleteById(id);

        Optional<ClientEntity> deletedEntity = repository.findById(id);
        assertThat(deletedEntity).isPresent();
        assertThat(deletedEntity.get().getStatus()).isEqualTo(ClientStatus.INACTIVE);
    }


}