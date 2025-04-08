package com.sahansha.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data @AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountsDTO {

    @Pattern(regexp = "(^$|[0-9]{9})",message = "Account Number must be 9 digits")
    @NotEmpty(message = "Account Number can not be null or empty")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
