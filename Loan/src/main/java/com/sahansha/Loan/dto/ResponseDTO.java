package com.sahansha.Loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
    name = "Response",
    description = "Response"
)
public class ResponseDTO {

    @Schema(description = "Error Code")
    private String errorCode;

    @Schema(description = "Error Message")
    private String errorMessage;
}
