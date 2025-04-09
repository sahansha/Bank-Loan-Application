package com.sahansha.Loan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {

    private String errorCode;
    private String errorMessage;
}
