package com.pbs.controller;

import com.pbs.constants.AccountsConstants;
import com.pbs.dto.CustomerDto;
import com.pbs.dto.ErrorResponseDto;
import com.pbs.dto.ResponseDto;
import com.pbs.services.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API for Accounts",
        description = "CRUD REST API's in Accounts to CREATE, UPDATE, FETCH and DELETE account details"
)
@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService service;

    @Operation(
            summary = "Create account REST API",
            description = "Rest API to create a new Customer and Account, where we need not to pass account related parameters"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerCustomer(@Valid @RequestBody CustomerDto customerDto) {

        service.createAccounts(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "Rest API to fetch the details of a Customer and Account using mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must consists of 10 digits only")
                                                           String mobile) {

        CustomerDto customerDto = service.fetchCustomer(mobile);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Customer and Account REST API",
            description = "Rest API to update Customer and Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION_FAILED",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {

        boolean isUpdated = service.updateCustomer(customerDto);

        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(
            summary = "Delete Customer and Account REST API",
            description = "Rest API to delete Customer and Account details using customerDto object"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION_FAILED"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@Valid @RequestBody CustomerDto customerDto) {

        boolean isDeleted = service.deleteCustomer(customerDto);

        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Delete Customer and Account REST API",
            description = "Rest API to delete Customer and Account details using mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION_FAILED"
            )
    })
    @DeleteMapping("/del")
    public ResponseEntity<ResponseDto> delCustomer(@RequestParam
                                                   @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must consists of 10 digits only")
                                                   String mobile) {
        boolean isDeleted = service.deleteCustomer(mobile);

        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
