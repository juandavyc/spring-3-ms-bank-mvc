package com.juandavyc.accounts.infrastructure.persistence.repository;

import com.juandavyc.accounts.domian.model.enums.AccountType;
import com.juandavyc.accounts.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    List<AccountEntity> findByClientId(UUID clientId);

    boolean existsByClientIdAndType(UUID clientId, AccountType type);


}
