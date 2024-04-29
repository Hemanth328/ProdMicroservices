package com.pbs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response Info",
        description = "Schema to hold the Successful Response Info"
)
public class ResponseDto {

    @Schema(
            name = "Success Status Code",
            description = "To hold the value of success code",
            example = "200 or 201 etc"
    )
    private String successCode;

    @Schema(
            name = "Success Status Message",
            description = "To hold the value of success message"
    )
    private String statusMsg;
}
