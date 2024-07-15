package com.chamoddulanjana.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    private String id;

    @Email
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Length(min = 5, max = 20, message = "Email must be between 5 and 20 characters")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotNull(message = "Password is required")
    @Length(min = 7, max = 15, message = "Password must be between 5 and 20 characters")
    private String password;
}
