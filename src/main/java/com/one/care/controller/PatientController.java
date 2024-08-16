package com.one.care.controller;

import com.one.care.dto.HospitalSignUpInputParams;
import com.one.care.dto.HospitalUpdateInputParams;
import com.one.care.dto.PatientSignUpInputParams;
import com.one.care.dto.VitalsDTO;
import com.one.care.model.Disease;
import com.one.care.repository.VitalsRepository;
import com.one.care.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contains the REST APIs for Patient profile.
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    VitalsRepository vitalsRepository;

    /**
     * Performs Sign up for a new hospital.
     * @param patientSignUpInputParams input hospital profile
     * @return response messages as a ResponseEntity
     */
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity signUpPatient(@Valid @RequestBody PatientSignUpInputParams patientSignUpInputParams) {
        return patientService.signUpPatient(patientSignUpInputParams);
    }

    /**
     * Fetches patient profile along with departments and doctor details.
     * @param userId input userId
     * @return response messages as a ResponseEntity
     */
    @GetMapping(value = "/fetch-profile/{userId}" , produces = "application/json")
    public ResponseEntity fetchPatientProfile(@PathVariable("userId") String userId) {
        return patientService.fetchPatientProfile(userId);
    }

    /**
     * update patient profile details based on userId.
     * @param patientSignUpInputParams input patient profile
     * @return response messages as a ResponseEntity
     */
    @PutMapping(value = "/update-profile" , consumes = "application/json", produces = "application/json")
    public ResponseEntity updatePatientDetails(@RequestBody PatientSignUpInputParams patientSignUpInputParams) {
        return patientService.updatePatientDetails(patientSignUpInputParams);
    }


    /**
     * Fetches vital details.
     * @param patientId input userId
     * @return response messages as a ResponseEntity
     */
    @GetMapping(value = "/fetch-vitals/{patientId}" , produces = "application/json")
    public ResponseEntity fetchVitals(@PathVariable("patientId") String patientId) {
        return patientService.fetchVitals(patientId);
    }

    /**
     * Performs add a new vital.
     * @param vitalsDTO input vitals
     * @return response messages as a ResponseEntity
     */
    @PostMapping(value = "/add-vital", consumes = "application/json", produces = "application/json")
    public ResponseEntity addNewVital(@RequestBody VitalsDTO vitalsDTO) {
        return patientService.addNewVital(vitalsDTO);
    }

    /**
     * Performs update vital.
     * @param vitalsDTO input vitals
     * @return response messages as a ResponseEntity
     */
    @PutMapping(value = "/edit-vital", consumes = "application/json", produces = "application/json")
    public ResponseEntity editVital(@RequestBody VitalsDTO vitalsDTO) {
        return patientService.updateVital(vitalsDTO);
    }

    /**
     * Fetches disease details.
     * @param patientId input userId
     * @return response messages as a ResponseEntity
     */
    @GetMapping(value = "/fetch-diseases/{patientId}" , produces = "application/json")
    public ResponseEntity fetchDiseases(@PathVariable("patientId") String patientId) {
        return patientService.fetchDiseases(patientId);
    }

    /**
     * Performs add a new disease.
     * @param disease input disease
     * @return response messages as a ResponseEntity
     */
    @PostMapping(value = "/add-disease", consumes = "application/json", produces = "application/json")
    public ResponseEntity addNewDisease(@RequestBody Disease disease) {
        return patientService.addNewDisease(disease);
    }

    /**
     * Performs update disease.
     * @param disease input disease
     * @return response messages as a ResponseEntity
     */
    @PutMapping(value = "/edit-disease", consumes = "application/json", produces = "application/json")
    public ResponseEntity editDisease(@RequestBody Disease disease) {
        return patientService.updateDisease(disease);
    }
}
