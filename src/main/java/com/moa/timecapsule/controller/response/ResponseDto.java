package com.moa.timecapsule.controller.response;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<D> {
    private final HttpStatus code;
    private final String msg;
    private final D data;
}
