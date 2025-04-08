package com.sahansha.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data @AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(
        name = "Account",
        description = "Entity to Hold Account Details"
)
public class AccountsDTO {

    @Schema(
            description = "Account Number for Bank Loan application",
            example = "1234567891"
    )
    @Pattern(regexp = "(^$|[0-9]{9})",message = "Account Number must be 9 digits")
    @NotEmpty(message = "Account Number can not be null or empty")
    private Long accountNumber;

    @Schema(
            description = "Account Type for Bank Loan application",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address for Bank Loan application",
            example = "143 Vijay Nagar"
    )
    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
