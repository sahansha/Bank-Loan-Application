package com.sahansha.accounts.controller;

import com.sahansha.accounts.constants.AccountsConstants;
import com.sahansha.accounts.dto.CustomerDTO;
import com.sahansha.accounts.dto.ResponseDTO;
import com.sahansha.accounts.service.IAccountsService;
import com.sahansha.accounts.service.impl.AccountsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/accounts",produces = "application/json")
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD Accounts API",
        description = "Accounts API for CRUD operations i.e. Create,Read,Update,Delete Customers and their account"
)
public class AccountController {

    private final IAccountsService iAccountsService;

    @Operation(
            method = "Create Account",
            summary = "Endpoint to Create Customer and Accounts",
            description = "This endpoint is used to create customer and account in Bank Loan application"
    )
    @ApiResponse(
            responseCode = "201",
            description = AccountsConstants.MESSAGE_201
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO)
    {
        iAccountsService.createAccount(customerDTO);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @Operation(
            method = "Fetch Account",
            summary = "Endpoint to Fetch Customer with Account details",
            description = "This endpoint is used to fetch customer and account in Bank Loan application"
    )
    @ApiResponse(
            responseCode = "200",
            description = AccountsConstants.MESSAGE_200
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                        @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                                                               String mobileNumber) {
        return ResponseEntity.ok(iAccountsService.fetchAccount(mobileNumber));
    }

    @Operation(
            method = "Update Account",
            summary = "Endpoint to Update Customer and Accounts",
            description = "This endpoint is used to Update customer and account in Bank Loan application"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = AccountsConstants.MESSAGE_200
            ),@ApiResponse(
                    responseCode = "500",
                    description = AccountsConstants.MESSAGE_500
            )
            }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = iAccountsService.updateAccount(customerDTO);
        if(isUpdated)
        {
            return ResponseEntity.ok(new ResponseDTO(AccountsConstants.STATUS_200
                    ,AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }

    }

    @Operation(
            method = "Delete Account",
            summary = "Endpoint to Delete Customer and Accounts",
            description = "This endpoint is used to Delete customer and account in Bank Loan application"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = AccountsConstants.MESSAGE_200
            ),@ApiResponse(
                    responseCode = "500",
                    description = AccountsConstants.MESSAGE_500
            )
            }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam
                  @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                  String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted)
        {
            return ResponseEntity.ok(new ResponseDTO(AccountsConstants.STATUS_200
                    ,AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }

}
