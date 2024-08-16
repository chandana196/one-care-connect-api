package com.one.care.controller;

import com.one.care.dto.*;
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

    /**
     * Inserts new doctor.
     * @param doctorInputParams input doctor details
     * @return response messages as a ResponseEntity
     */
    @PostMapping(value = "/add-doctor", consumes = "application/json", produces = "application/json")
    public ResponseEntity addDoctor(@Valid @RequestBody DoctorInputParams doctorInputParams) {
        return hospitalService.addDoctor(doctorInputParams);
    }

    /**
     * Edit a doctor.
     * @param doctorInputParams input doctor details
     * @return response messages as a ResponseEntity
     */
    @PutMapping(value = "/edit-doctor", consumes = "application/json", produces = "application/json")
    public ResponseEntity editDoctor(@Valid @RequestBody DoctorInputParams doctorInputParams) {
        return hospitalService.editDoctor(doctorInputParams);
    }
}