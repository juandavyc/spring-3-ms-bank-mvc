package com.juandavyc.accounts.application.mapper;

import com.juandavyc.accounts.application.dto.TransactionCommand;
import com.juandavyc.accounts.application.dto.TransactionResponse;
import com.juandavyc.accounts.domian.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionApplicationMapper {

    Transaction toDomain(TransactionCommand command);

    TransactionResponse toResponse(Transaction transaction);
    List<TransactionResponse> toResponseList(List<Transaction> transactions);
}
