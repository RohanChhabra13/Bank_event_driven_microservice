package com.bank.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty !")
    @Size(min=2, max=30, message="The length of the customer name should me between 2 and 30 characters !")
    private String name;

    @NotEmpty(message = "email Id cannot be null or empty !")
    @Email(message = "Invalid Email Address!")
    private String email;

    @JsonProperty("mobile_number")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number !")
    @NotEmpty(message = "mobile number cannot be null or empty !")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
