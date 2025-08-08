package com.bank.accounts.controller;

import com.bank.accounts.contants.AccountsConstants;
import com.bank.accounts.dto.AccountsContactInfoDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.dto.ErrorResponseDto;
import com.bank.accounts.dto.ResponseDto;
import com.bank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in Bank",
        description = "Create, UPDATE, FETCH and DELETE account details"
)
@RestController
@RequestMapping(path = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
//@AllArgsConstructor
@Validated
public class AccountsController {

    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService){
        this.iAccountsService = iAccountsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Operation(summary = "Create Account REST API", description = "Creates new Account and Customer in Bank")
    @ApiResponse(responseCode = "201", description = "Status Created!")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccounnt(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Operation(summary = "Fetch Account Details REST API", description = "REST API to fetch Customer and Account details based on mobile number")
    @ApiResponse(responseCode = "200", description = "Status OK")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                               String mobileNumber){
       CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
       return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "UPDATE Account Details REST API", description = "REST API to update Customer and Account details based on mobile number")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Status OK"),
    @ApiResponse(responseCode = "500", description = "Status Internal Server Error")})
    @PutMapping("/update")
    public  ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
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

    @Operation(summary = "DELETE Account Details REST API", description = "REST API to delete Customer and Account details based on mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status OK"),
            @ApiResponse(responseCode = "500", description = "Status Internal Server Error")})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get build Information",
            description = "REST API to fetch Customer and Account details based on mobile number")
    @ApiResponses({
    @ApiResponse(
            responseCode = "200",
            description = "Status OK"),
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Status internal server error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))})
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }


    @Operation(
            summary = "Get Java Version",
            description = "Get Java Version")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))})
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Get Contact Info")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))})
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}
