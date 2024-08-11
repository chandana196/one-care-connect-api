package com.one.care.controller;

import com.one.care.dto.HospitalSignInInputParams;
import com.one.care.dto.HospitalSignUpInputParams;
import com.one.care.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contains the REST APIs for Hospital profile.
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    /**
     * Performs Sign up for a new hospital.
     * @param hospitalSignUpInputParams input hospital profile
     * @return User ID as a ResponseEntity
     */
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity signUpNewUser(@Valid @RequestBody HospitalSignUpInputParams hospitalSignUpInputParams) {
        return hospitalService.signUpNewUser(hospitalSignUpInputParams);
    }

    /**
     * Performs Sign in for an exiting hospital.
     * @param hospitalSignInInputParams input hospital profile
     * @return validation messages as a ResponseEntity
     */
    @PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity signInUser(@Valid @RequestBody HospitalSignInInputParams hospitalSignInInputParams) {
        return hospitalService.signInUser(hospitalSignInInputParams);
    }

    /**
     * Performs forget password for an exiting hospital.
     * @param userId input userId
     * @return validation messages as a ResponseEntity
     */
    @GetMapping(value = "/forgot-password/{userId}" , produces = "application/json")
    public ResponseEntity forgotPassword(@PathVariable("userId") String userId) {
        return hospitalService.forgotPassword(userId);
    }
}