package com.juandavyc.core.mapper;

import com.juandavyc.core.dto.response.ResponseDto;
import com.juandavyc.core.shared.ResponseCode;

public class ResponseMapper<T> {

    public static <T, R extends ResponseCode> ResponseDto<T> response(R responseCode, T data) {
        return new ResponseDto<>(
                responseCode.getValue(),
                responseCode.getStatus().name(),
                responseCode.getMessage(),
                data
        );
    }

}
