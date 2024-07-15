package com.chamoddulanjana.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OwnerDTO {

    private String id;

    @NotEmpty(message = "name is required")
    @NotNull(message = "name is required")
    @Length(min = 3, max = 100, message = "name must be between 3 and 100 characters")
    private String name;

    @NotEmpty(message = "Identity number is required")
    @NotNull(message = "Identity number is required")
    @Length(min = 10, max = 10, message = "Identity number must be 10 characters")
    private String identityNumber;

    @NotEmpty(message = "Phone number is required")
    @NotNull(message = "Phone number is required")
    @Length(min = 10, max = 12, message = "Phone number must be between 10 and 12 characters")
    private String phoneNumber;

    @NotEmpty(message = "Address is required")
    @NotNull(message = "Address is required")
    @Length(min = 3, max = 30, message = "Address must be between 3 and 30 characters")
    private String address;

    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    @Length(min = 3, max = 30, message = "Email must be between 3 and 30 characters")
    @Email
    private String email;


}
