package com.pbs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            name = "Account Number",
            description = "Account number of the customer",
            example = "00111112222365L"
    )
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "($|[0-9]{10})", message = "Account number must consists of 10 digits only")
    private Long accountNumber;

    @Schema(
            name = "Account Type",
            description = "Account type of the customer",
            example = "Savings or Current or Trading"
    )
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            name = "Branch Address",
            description = "Customer Branch Location",
            example = "Hyderabad Main Branch"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
