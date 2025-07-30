package com.bank.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema for Customer and Account info"
)
public class CustomerDto {

    @Schema(description = "Name of customer")
    @NotEmpty(message = "Name cannot be null or empty !")
    @Size(min=2, max=30, message="The length of the customer name should me between 2 and 30 characters !")
    private String name;

    @Schema(description = "Email of customer")
    @NotEmpty(message = "email Id cannot be null or empty !")
    @Email(message = "Invalid Email Address!")
    private String email;

    @Schema(description = "Mobile Number of customer")
    @JsonProperty("mobile_number")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number !")
    @NotEmpty(message = "mobile number cannot be null or empty !")
    private String mobileNumber;

    @Schema(name = "Account details")
    private AccountsDto accountsDto;
}
