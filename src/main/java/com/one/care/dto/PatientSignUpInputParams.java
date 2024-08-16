package com.one.care.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.aot.hint.annotation.RegisterReflection;

import java.time.LocalDate;

/**
 * Contains input params for Hospital sign up.
 */
@Data
@RegisterReflection
public class PatientSignUpInputParams {

    private String userId;

    @NotBlank(message = "Patient Name is mandatory")
    @Size(min = 1, max = 50, message = "Patient Name must be between 1 and 50 characters")
    private String name;

    @NotBlank(message = "Patient Name is mandatory")
    @Size(min = 1, max = 50, message = "Father/Spouse Name must be between 1 and 50 characters")
    private String fsName;

    @NotNull(message = "DOB is mandatory")
    private LocalDate dob;

    private String bGroup;

    @NotBlank(message = "Patient Aadhar is mandatory")
    private String aadhar;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobile;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 255, message = "Address cannot be more than 255 characters")
    private String address;
}
