package com.sahansha.Loan.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponseDTO {

    private String apiPath;
    private HttpStatus errorCode;
    private String message;
    private LocalDateTime errorTime;
}
