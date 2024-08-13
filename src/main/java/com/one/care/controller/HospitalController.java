package com.one.care.controller;

import com.one.care.dto.DepartmentInputParams;
import com.one.care.dto.HospitalSignInInputParams;
import com.one.care.dto.HospitalSignUpInputParams;
import com.one.care.dto.HospitalUpdateInputParams;
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
     * @return response messages as a ResponseEntity
     */
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity signUpNewUser(@Valid @RequestBody HospitalSignUpInputParams hospitalSignUpInputParams) {
        return hospitalService.signUpNewUser(hospitalSignUpInputParams);
    }

    /**
     * Performs Sign in for an exiting hospital.
     * @param hospitalSignInInputParams input hospital profile
     * @return response messages as a ResponseEntity
     */
    @PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity signInUser(@Valid @RequestBody HospitalSignInInputParams hospitalSignInInputParams) {
        return hospitalService.signInUser(hospitalSignInInputParams);
    }

    /**
     * Performs forget password for an exiting hospital.
     * @param userId input userId
     * @return response messages as a ResponseEntity
     */
    @GetMapping(value = "/forgot-password/{userId}" , produces = "application/json")
    public ResponseEntity forgotPassword(@PathVariable("userId") String userId) {
        return hospitalService.forgotPassword(userId);
    }

    /**
     * Fetches hospital profile along with departments and doctor details.
     * @param userId input userId
     * @return response messages as a ResponseEntity
     */
    @GetMapping(value = "/fetch-profile/{userId}" , produces = "application/json")
    public ResponseEntity fetchHospitalProfile(@PathVariable("userId") String userId) {
        return hospitalService.fetchHospitalProfile(userId);
    }

    /**
     * Fetches hospital profile details based on userId.
     * @param hospitalUpdateInputParams input hospital profile
     * @return response messages as a ResponseEntity
     */
    @PutMapping(value = "/update-profile" , consumes = "application/json", produces = "application/json")
    public ResponseEntity updateHospitalDetails(@RequestBody HospitalUpdateInputParams hospitalUpdateInputParams) {
        return hospitalService.updateHospitalDetails(hospitalUpdateInputParams);
    }

    /**
     * Inserts new department.
     * @param departmentInputParams input department details
     * @return response messages as a ResponseEntity
     */
    @PostMapping(value = "/add-department", consumes = "application/json", produces = "application/json")
    public ResponseEntity addDepartment(@Valid @RequestBody DepartmentInputParams departmentInputParams) {
        return hospitalService.addDepartment(departmentInputParams);
    }


    //edit doctor details

    //Add doctor profile is not there, that is required or not?

}