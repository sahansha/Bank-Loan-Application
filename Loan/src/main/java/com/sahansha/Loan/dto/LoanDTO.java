package com.sahansha.Loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
    name = "Loan",
    description = "Loan details"
)
public class LoanDTO {

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Schema(description = "Mobile number", example = "1234567890")
    @NotEmpty(message = "Mobile number can not be null or empty")
    private String mobileNumber;

    @Schema(description = "Loan number", example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{9})",message = "Loan number must be 9 digits")
    @NotEmpty(message = "Loan number can not be null or empty")
    private String loanNumber;

    @Schema(description = "Loan type", example = "Home Loan")
    @NotEmpty(message = "Loan type can not be null or empty")
    private String loanType;

    @Schema(description = "Total loan amount", example = "1000000")
    @Positive(message = "Total loan amount should be greater than 0")
    private Long totalLoan;

    @Schema(description = "Amount paid", example = "500000")
    @PositiveOrZero(message = "Amount paid should be greater than or equal to 0")
    private Long amountPaid;

    @Schema(description = "Outstanding amount", example = "500000")
    @PositiveOrZero(message = "Outstanding amount should be greater than or equal to 0")
    private Long outstandingAmount;

}
