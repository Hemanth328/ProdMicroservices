package com.pbs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold Successful Response Information"
)
public class ResponseDto {

    @Schema(
            name = "Response Code",
            description = "Success status code",
            example = "200, 201, etc"
    )
    private String successCode;

    @Schema(
            name = "Status Message",
            description = "Success status message",
            example = "Account created successfully"
    )
    private String statusMsg;
}
