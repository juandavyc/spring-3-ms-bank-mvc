package com.juandavyc.clients.infrastructure.persistence.repository;

import com.juandavyc.clients.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByIdentification(String identification);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, UUID id);


}
