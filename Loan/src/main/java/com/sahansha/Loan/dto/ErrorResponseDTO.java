package com.sahansha.Loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(
    name = "ErrorResponse",
    description = "Error Response"
)
public class ErrorResponseDTO {

    @Schema(
        description = "API Path",
        example = "/api/v1/loan/create"
    )
    private String apiPath;

    @Schema(
        description = "Error Code",
        example = "500"
    )
    private HttpStatus errorCode;

    @Schema(
        description = "Error Message",
        example = "Internal Server Error"
    )
    private String message;

    @Schema(
        description = "Error Time",
        example = "2023-08-01T10:00:00"
    )
    private LocalDateTime errorTime;
}
