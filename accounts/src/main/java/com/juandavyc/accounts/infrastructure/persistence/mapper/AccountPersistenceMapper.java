package com.juandavyc.accounts.infrastructure.persistence.mapper;

import com.juandavyc.accounts.domian.model.Account;
import com.juandavyc.accounts.infrastructure.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountPersistenceMapper {

    AccountEntity toEntity(Account account);
    Account toDomain(AccountEntity accountEntity);

}
