package com.sahansha.Loan.controller;

import com.sahansha.Loan.constants.LoansConstants;
import com.sahansha.Loan.dto.ErrorResponseDTO;
import com.sahansha.Loan.dto.LoanContactsInfoDTO;
import com.sahansha.Loan.dto.LoanDTO;
import com.sahansha.Loan.dto.ResponseDTO;
import com.sahansha.Loan.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
@Tag(
        name =  "Loan API",
        description = "Loan API for CRUD operations i.e. create, fetch, update, delete"
)
public class LoansController {

    private final ILoanService loanService;

    @Autowired
    private LoanContactsInfoDTO loanContactsInfoDTO;

    public LoansController(ILoanService loanService) {    this.loanService = loanService;    }

    @Operation(
            summary = "Create a new loan",
            description = "This API is used to create a new loan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber)
    {
        loanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDTO.builder()
                        .errorCode(LoansConstants.STATUS_201)
                        .errorMessage(LoansConstants.MESSAGE_201)
                        .build());
    }

    @Operation(
            summary = "Fetch loan details",
            description = "This API is used to fetch loan details"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = LoansConstants.MESSAGE_200
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoanDTO> fetchLoan(@RequestParam
                                                 @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                 String mobileNumber)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(loanService.fetchLoan(mobileNumber));
    }

    @Operation(
            summary = "Update loan details",
            description = "This API is used to update loan details"
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "417",
                            description = LoansConstants.MESSAGE_417_UPDATE
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode ="200",
                            description = LoansConstants.MESSAGE_200
                    )
            }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoanDTO loansDto) {
        boolean isUpdated = loanService.updateLoan(loansDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .errorCode(LoansConstants.STATUS_200)
                            .errorMessage(LoansConstants.MESSAGE_200)
                            .build());
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDTO.builder()
                            .errorCode(LoansConstants.STATUS_417)
                            .errorMessage(LoansConstants.MESSAGE_417_UPDATE)
                            .build());
        }
    }

   @Operation(
           summary = "Delete loan details",
           description = "This API is used to delete loan details"
   )
   @ApiResponses(
           value = {
                   @ApiResponse(
                           responseCode = "417",
                           description = LoansConstants.MESSAGE_417_DELETE
                   ),
                   @ApiResponse(
                           responseCode = "500",
                           description = "Internal Server Error",
                           content = @Content(
                                   schema = @Schema(implementation = ErrorResponseDTO.class)
                           )
                   ),
                   @ApiResponse(
                           responseCode ="200",
                           description = LoansConstants.MESSAGE_200
                   )
           }
   )

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoanDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = loanService.deleteLoan(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .errorCode(LoansConstants.STATUS_200)
                            .errorMessage(LoansConstants.MESSAGE_200)
                            .build());
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDTO.builder()
                            .errorCode(LoansConstants.STATUS_417)
                            .errorMessage(LoansConstants.MESSAGE_417_DELETE)
                            .build());
        }
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<LoanContactsInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanContactsInfoDTO);
    }
}
