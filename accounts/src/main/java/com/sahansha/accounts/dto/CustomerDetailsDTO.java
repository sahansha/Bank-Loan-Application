package com.sahansha.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetailsDTO",
        description = "Customer Details"
)
public class CustomerDetailsDTO {

    @Schema(
            description = "Customer Name for Bank Account",
            example = "Sahansha"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5,max = 30,message = "The length of customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Customer Email for Bank Account",
            example = "sak@gmail.com"
    )
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email format is not valid")
    private String email;

    @Schema(
            description = "Mobile Number for Bank Account",
            example = "9876543212"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @NotEmpty(message = "Mobile Number can not be null or empty")
    private String mobileNumber;

    @Schema(
            description = "Bank Account details"
    )
    private AccountsDTO accountsDTO;

    @Schema(
            description = "Loan details"
    )
    private LoanDTO loansDTO;

    @Schema(
            description = "Card details"
    )
    private CardsDto cardsDto;

}
