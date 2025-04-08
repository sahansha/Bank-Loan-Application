package com.sahansha.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5,max = 30,message = "The length of customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email format is not valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @NotEmpty(message = "Mobile Number can not be null or empty")
    private String mobileNumber;
    private AccountsDTO accountsDTO;
}
