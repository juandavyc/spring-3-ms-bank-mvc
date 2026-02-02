package com.juandavyc.accounts.infrastructure.rest.mapper;

import com.juandavyc.accounts.application.dto.transaction.TransactionCommand;
import com.juandavyc.accounts.application.dto.transaction.TransactionResponse;
import com.juandavyc.accounts.infrastructure.rest.dto.TransactionRestRequest;
import com.juandavyc.accounts.infrastructure.rest.dto.TransactionRestResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionRestMapper {

    TransactionCommand toCommand(TransactionRestRequest request);

    TransactionRestResponse toRestResponse(TransactionResponse response);

    List<TransactionRestResponse> toResponseList(List<TransactionResponse> entities);

}
