package com.one.care.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.aot.hint.annotation.RegisterReflection;

/**
 * Contains input params for Hospital sign in.
 */
@Data
@RegisterReflection
public class HospitalSignInInputParams {

    @NotBlank(message = "User ID is mandatory")
    @Size(min = 10, max = 10, message = "User ID must be 10 characters long")
    private String userId;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters long")
    private String password;
}
