package com.sahansha.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor
@Builder
public class ResponseDTO {
    private String statusCode;
    private String statusMsg;
}
