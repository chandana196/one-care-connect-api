package com.one.care.service;

import com.one.care.dto.HospitalSignInInputParams;
import com.one.care.dto.HospitalSignUpInputParams;
import com.one.care.dto.ResponseMessage;
import com.one.care.model.Hospital;
import com.one.care.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Contains business logic for hospital profile.
 */
@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    /**
     * Performs Sign up for a new hospital.
     * @param hospitalSignUpInputParams input hospital profile
     * @return User ID as a ResponseEntity
     */
    public ResponseEntity signUpNewUser(HospitalSignUpInputParams hospitalSignUpInputParams) {
        Hospital hospital = new Hospital();
        ResponseMessage responseMessage = new ResponseMessage();
        hospital.setName(hospitalSignUpInputParams.getHospitalName());
        hospital.setEmailId(hospitalSignUpInputParams.getEmailId());
        hospital.setAddress(hospitalSignUpInputParams.getAddress());
        hospital.setOrgType(hospitalSignUpInputParams.getOrgType());
        hospital.setRegdNo(hospitalSignUpInputParams.getRegdNo());
        String userId = UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
        hospital.setUserId(userId);
        hospital.setPassword(hospitalSignUpInputParams.getPassword());
        hospital.setContactNo(hospitalSignUpInputParams.getContactNo());
        hospitalRepository.save(hospital);
        responseMessage.setResponseMessage("UserId is " + userId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /**
     * Performs Sign in for an exiting hospital.
     * @param hospitalSignInInputParams input hospital profile
     * @return validation messages as a ResponseEntity
     */
    public ResponseEntity signInUser(HospitalSignInInputParams hospitalSignInInputParams) {
        ResponseMessage responseMessage = new ResponseMessage();
        Hospital hospital = hospitalRepository.findByUserId(hospitalSignInInputParams.getUserId());
        if (hospital != null) {
            if (hospital.getPassword().equals(hospitalSignInInputParams.getPassword())) {
                responseMessage.setResponseMessage("Authenticated");
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            } else {
                responseMessage.setResponseMessage("Wrong password received");
                return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
            }
        } else {
            responseMessage.setResponseMessage("User not found");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Performs forget password for an exiting hospital.
     * @param userId input userId
     * @return validation messages as a ResponseEntity
     */
    public ResponseEntity forgotPassword(String userId) {
        ResponseMessage responseMessage = new ResponseMessage();
        Hospital hospital = hospitalRepository.findByUserId(userId);
        if (hospital != null) {
            String password = hospital.getPassword();
            responseMessage.setResponseMessage("Password is " + password);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            responseMessage.setResponseMessage("User not found");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
