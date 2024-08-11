package com.one.care.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.aot.hint.annotation.RegisterReflection;

@Data
@RegisterReflection
public class HospitalSignUpInputParams {

    @NotBlank(message = "Hospital Name is mandatory")
    @Size(min = 1, max = 50, message = "Hospital Name must be between 1 and 50 characters")
    private String hospitalName;

    @NotBlank(message = "Email ID is mandatory")
    @Email(message = "Email should be valid")
    private String emailId;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 255, message = "Address cannot be more than 255 characters")
    private String address;

    @NotBlank(message = "Organization type is mandatory")
    @Size(max = 10, message = "Organization type must be between 3 and 50 characters")
    private String orgType;

    @NotBlank(message = "Registration number is mandatory")
    @Size(min = 10, max = 10, message = "Registration number must be between 3 and 50 characters")
    private String regdNo;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number must be between 10 and 15 digits")
    private String contactNo;
}
