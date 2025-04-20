package com.sahansha.accounts.controller;

import com.sahansha.accounts.constants.AccountsConstants;
import com.sahansha.accounts.dto.CustomerDetailsDTO;
import com.sahansha.accounts.dto.ErrorResponseDTO;
import com.sahansha.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
    name = "Customer Details",
    description = "Customer Details APIs"
)
@RequestMapping("/api/v1/customer")
@Validated
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
        method = "Fetch Customer Details",
        summary = "Fetch Customer Details using mobile number",
        description = "Fetch Customer Details"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                    responseCode = "200",
                    description = AccountsConstants.MESSAGE_200),
                    @ApiResponse(
                    responseCode = "500",
                    description =  "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                                                                   String mobileNumber)
    {
        CustomerDetailsDTO customerDetailsDTO = customerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.ok(customerDetailsDTO);
    }

}
