package com.pbs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Customer",
        description = "Schema to hold Account and Customer information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "Hemanth Kumar"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @NotBlank
    @Size(min = 5, max = 40, message = "The length of the name should be in between 5 and 30 characters")
    private String name;

    @Schema(
            description = "MailId of the Customer",
            example = "hemanthkumar@gmail.com"
    )
    @NotEmpty(message = "Email Id cannot be a null or empty")
    @Email(message = "please enter valid mailId")
    private String email;

    @Schema(
            description = "Mobile Number of the Customer",
            example = "0123456789"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must consists of 10 digits only")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;

}
