package com.juandavyc.accounts.infrastructure.persistence.mapper;

import com.juandavyc.accounts.domian.model.Transaction;
import com.juandavyc.accounts.infrastructure.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionPersistenceMapper {

    TransactionEntity toEntity(Transaction transaction);
    Transaction toDomain(TransactionEntity  transactionEntity);

}
