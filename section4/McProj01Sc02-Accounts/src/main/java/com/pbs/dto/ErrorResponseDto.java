package com.pbs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold Error Response information"
)
public class ErrorResponseDto {

    @Schema(
            name = "Api Path",
            description = "In which endpoint the api execution failed when invoked by the client"
    )
    private String apiPath;

    @Schema(
            name = "Error Message",
            description = "Error message of the failed path"
    )
    private String errorMsg;

    @Schema(
            name = "Error Code",
            description = "Error code of the failed path"
    )
    private HttpStatus errorCode;

    @Schema(
            name = "Error Time",
            description = "On which date and time the incident has unfolded"
    )
    private LocalDateTime errorTime;

}
