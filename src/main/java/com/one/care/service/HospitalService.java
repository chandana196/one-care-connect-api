package com.one.care.service;

import com.one.care.dto.*;
import com.one.care.model.Department;
import com.one.care.model.Hospital;
import com.one.care.repository.DepartmentRepository;
import com.one.care.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Contains business logic for hospital profile.
 */
@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * Performs Sign up for a new hospital.
     * @param hospitalSignUpInputParams input hospital profile
     * @return response messages as a ResponseEntity
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
     * @return response messages as a ResponseEntity
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
     * @return response messages as a ResponseEntity
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

    /**
     * Fetches hospital profile along with departments and doctor details.
     * @param userId input userId
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity fetchHospitalProfile(String userId) {
        List<ProfileResponseDTO> profileResponseDTOS = hospitalRepository.findHospitalWithDepartmentsAndDoctors(userId);
        return new ResponseEntity<>(profileResponseDTOS, HttpStatus.OK); //TODO: fetching empty result and throwing error.
    }

    /**
     * Updates the Hospital Details.
     * @param hospitalUpdateInputParams input data for update
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity updateHospitalDetails(HospitalUpdateInputParams hospitalUpdateInputParams) {
        ResponseMessage responseMessage = new ResponseMessage();
        Hospital hospital = new Hospital();
        hospital.setUserId(hospitalUpdateInputParams.getUserId());
        hospital.setName(hospitalUpdateInputParams.getHospitalName());
        hospital.setEmailId(hospitalUpdateInputParams.getEmailId());
        hospital.setAddress(hospitalUpdateInputParams.getAddress());
        hospital.setOrgType(hospitalUpdateInputParams.getOrgType());
        hospital.setRegdNo(hospitalUpdateInputParams.getRegdNo());
        hospital.setContactNo(hospitalUpdateInputParams.getContactNo());
        hospitalRepository.save(hospital);//TODO: password is null, so this save will not work, use native query instead.
        responseMessage.setResponseMessage("Update Hospital Data is successful.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /**
     * Inserts new department.
     * @param departmentInputParams input department details
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity addDepartment(DepartmentInputParams departmentInputParams) {
        Department department = new Department();
        ResponseMessage responseMessage = new ResponseMessage();
        department.setDeptName(departmentInputParams.getDeptName());
        department.setHospitalId(departmentInputParams.getHospitalId());
        Department departmentResponse = departmentRepository.save(department);
        responseMessage.setResponseMessage("Department added successfully with Dept ID "+ departmentResponse.getDeptId());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
