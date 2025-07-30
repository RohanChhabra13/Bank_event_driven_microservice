package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data @AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema for Response info"
)
public class ResponseDto {

    @Schema(description = "Status code")
    private String statusCode;

    @Schema(description = "Status Message")
    private String statusMsg;
}
