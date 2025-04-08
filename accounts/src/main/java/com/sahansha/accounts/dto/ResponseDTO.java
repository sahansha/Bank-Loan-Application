package com.sahansha.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor
@Builder
@Schema(name = "Response",
        description = "Holds Response Details")
public class ResponseDTO {
    @Schema(
            description = "send status code for response"
    )
    private String statusCode;

    @Schema(
            description = "send message for successful response"
    )
    private String statusMsg;
}
