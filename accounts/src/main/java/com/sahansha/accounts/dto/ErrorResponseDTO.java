package com.sahansha.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Builder
@Schema(name = "ErrorResponse",
        description = "Holds Error response in case of Exception")
public class ErrorResponseDTO {
    @Schema(
            description = "sends api path where error occurred"
    )
    private String apiPath;

    @Schema(
            description = "sends error code"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "sends error message"
    )
    private String errorMessage;

    @Schema(
            description = "sends time when the error occurred"
    )
    private LocalDateTime errorTime;
}
