package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema for Account info"
)
public class AccountsDto {

    @Schema(name = "Account number")
    @NotEmpty(message = "Account number cannot be null or empty !")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid Account number !")
    private Long accountNumber;

    @Schema(name = "Account type", example = "Savings or Current")
    @NotEmpty(message = "Account type cannot be null or empty !")
    private String accountType;

    @Schema(name = "Branch Address")
    @NotEmpty(message = "Address cannot be null or empty !")
    private String branchAddress;
}

