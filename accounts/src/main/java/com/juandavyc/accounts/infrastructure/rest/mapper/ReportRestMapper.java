package com.juandavyc.accounts.infrastructure.rest.mapper;

import com.juandavyc.accounts.application.dto.ReportResponse;
import com.juandavyc.accounts.infrastructure.rest.dto.ReportRestResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportRestMapper {

    ReportRestResponse toResponse(ReportResponse reportResponse);

}
