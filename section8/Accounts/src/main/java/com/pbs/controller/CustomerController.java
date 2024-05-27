package com.pbs.controller;

import com.pbs.dto.CustomerDetailsDto;
import com.pbs.dto.ErrorResponseDto;
import com.pbs.services.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "REST API for Customer Details",
        description = "Customer Details REST API to fetch all the details of the customer details"
)
@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @Operation(
            summary = "Fetch Customer Details from the application",
            description = "Rest API to Customer Details from the application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must consists of 10 digits only")
                                                                   String mobileNumber) {

        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDetailsDto);
    }
}
