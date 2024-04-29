package com.pbs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response Info",
        description = "To hold the values of Error Response Information"
)
public class ErrorResponseDto {

    @Schema(
            name = "Error Response Code",
            description = "To hold the error response code"
    )
    private HttpStatus errorCode;

    @Schema(
            name = "API Path",
            description = "To describe in which API Error has unfolded"
    )
    private String apiPath;

    @Schema(
            name = "Error Message",
            description = "To know why the error has raised"
    )
    private String errorMsg;

    @Schema(
            name = "Error Time",
            description = "to describe at what time the error has taken place"
    )
    private LocalDateTime errorTime;
}
