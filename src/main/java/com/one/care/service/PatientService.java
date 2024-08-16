package com.one.care.service;

import com.one.care.dto.HospitalSignUpInputParams;
import com.one.care.dto.PatientSignUpInputParams;
import com.one.care.dto.ResponseMessage;
import com.one.care.dto.VitalsDTO;
import com.one.care.model.Disease;
import com.one.care.model.Hospital;
import com.one.care.model.Patient;
import com.one.care.model.Vitals;
import com.one.care.repository.DiseaseRepository;
import com.one.care.repository.PatientRepository;
import com.one.care.repository.VitalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Contains business logic for patient profile.
 */
@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VitalsRepository vitalsRepository;

    @Autowired
    DiseaseRepository diseaseRepository;

    /**
     * Performs Sign up for a new patient.
     * @param patientSignUpInputParams input patient profile
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity signUpPatient(PatientSignUpInputParams patientSignUpInputParams) {
        Patient patient = new Patient();
        ResponseMessage responseMessage = new ResponseMessage();
        String userId = "P" + UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase();
        patient.setUserId(userId);
        patient.setName(patientSignUpInputParams.getName());
        patient.setFsName(patientSignUpInputParams.getFsName());
        patient.setDob(patientSignUpInputParams.getDob());
        patient.setBGroup(patientSignUpInputParams.getBGroup());
        patient.setAadhar(patientSignUpInputParams.getAadhar());
        patient.setEmail(patientSignUpInputParams.getEmail());
        patient.setMobile(patientSignUpInputParams.getMobile());
        patient.setAddress(patientSignUpInputParams.getAddress());
        patientRepository.save(patient);
        responseMessage.setResponseMessage("Patient id is " + userId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /**
     * Updates the Patient Details.
     * @param patientSignUpInputParams input data for update
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity updatePatientDetails(PatientSignUpInputParams patientSignUpInputParams) {
        Patient patient = patientRepository.findByUserId(patientSignUpInputParams.getUserId());
        ResponseMessage responseMessage = new ResponseMessage();
        patient.setName(patientSignUpInputParams.getName());
        patient.setFsName(patientSignUpInputParams.getFsName());
        patient.setDob(patientSignUpInputParams.getDob());
        patient.setBGroup(patientSignUpInputParams.getBGroup());
        patient.setAadhar(patientSignUpInputParams.getAadhar());
        patient.setEmail(patientSignUpInputParams.getEmail());
        patient.setMobile(patientSignUpInputParams.getMobile());
        patient.setAddress(patientSignUpInputParams.getAddress());
        patientRepository.save(patient);
        responseMessage.setResponseMessage("Patient details updated successfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public ResponseEntity<Patient> fetchPatientProfile(String patientId) {
        Patient patient = patientRepository.findByUserId(patientId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    public ResponseEntity addNewVital(VitalsDTO vitalsDTO) {
        Vitals vitals = new Vitals();
        ResponseMessage responseMessage = new ResponseMessage();
        vitals.setPatientId(vitalsDTO.getPatientId());
        vitals.setVitalType(vitalsDTO.getVitalType());
        vitals.setVitalValue(vitalsDTO.getVitalValue());
        vitals.setVitalDate(Timestamp.from(Instant.now()));
        Vitals vitalsResponse = vitalsRepository.save(vitals);
        responseMessage.setResponseMessage("new vital added successsfully");
        return new ResponseEntity<>(vitalsResponse, HttpStatus.OK);
    }

    public ResponseEntity updateVital(VitalsDTO vitalsDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        Vitals vitals = vitalsRepository.findByVitalId(vitalsDTO.getVitalId());
        vitals.setVitalType(vitalsDTO.getVitalType());
        vitals.setVitalValue(vitalsDTO.getVitalValue());
        vitalsRepository.save(vitals);
        responseMessage.setResponseMessage("vital updated successsfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public ResponseEntity<List<Vitals>> fetchVitals(String patientId) {
        List<Vitals> vitalsList = vitalsRepository.findByPatientId(patientId);
        return new ResponseEntity<>(vitalsList, HttpStatus.OK);
    }

    public ResponseEntity<List<Disease>> fetchDiseases(String patientId) {
        List<Disease> diseaseList = diseaseRepository.findByPatientId(patientId);
        return new ResponseEntity<>(diseaseList, HttpStatus.OK);
    }

    public ResponseEntity updateDisease(Disease diseaseDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        Disease disease = diseaseRepository.findByDiseaseId(diseaseDTO.getDiseaseId());
        disease.setDiseaseName(diseaseDTO.getDiseaseName());
        disease.setDoctorName(diseaseDTO.getDoctorName());
        disease.setDoctorComments(diseaseDTO.getDoctorComments());
        disease.setIsIpd(diseaseDTO.getIsIpd());
        disease.setHospitalName(diseaseDTO.getHospitalName());
        disease.setDischargeDate(diseaseDTO.getDischargeDate());
        diseaseRepository.save(disease);
        responseMessage.setResponseMessage("disease updated successsfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public ResponseEntity addNewDisease(Disease diseaseDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        Disease disease = diseaseRepository.save(diseaseDTO);
        responseMessage.setResponseMessage("disease added successsfully");
        return new ResponseEntity<>(disease, HttpStatus.OK);
    }
}
