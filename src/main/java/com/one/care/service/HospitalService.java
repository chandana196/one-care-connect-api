package com.one.care.service;

import com.one.care.dto.*;
import com.one.care.model.Department;
import com.one.care.model.Doctor;
import com.one.care.model.Hospital;
import com.one.care.repository.DepartmentRepository;
import com.one.care.repository.DoctorRepository;
import com.one.care.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Contains business logic for hospital profile.
 */
@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

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
    public ResponseEntity<HospitalDTO> fetchHospitalProfile(String userId) {

        HospitalDTO hospitalDTO = new HospitalDTO();
        try {
            List<ProfileResponseDTO> profileResponseDTOS = hospitalRepository.findHospitalWithDepartmentsAndDoctors(userId);

            if (profileResponseDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Initialize the HospitalDTO
            hospitalDTO.setHospitalId(profileResponseDTOS.get(0).getUserId());
            hospitalDTO.setHospitalName(profileResponseDTOS.get(0).getName());
            hospitalDTO.setHospitalEmail(profileResponseDTOS.get(0).getEmailId());
            hospitalDTO.setHospitalAddress(profileResponseDTOS.get(0).getAddress());
            hospitalDTO.setHospitalType(profileResponseDTOS.get(0).getOrgType());
            hospitalDTO.setHospitalRegdNo(profileResponseDTOS.get(0).getRegdNo());
            hospitalDTO.setHospitalContact(profileResponseDTOS.get(0).getContactNo());

            // Create a map to store departments, where the key is the department ID
            Map<Integer, DepartmentDTO> departmentMap = new HashMap<>();

            // Iterate over the profileResponseDTOS to populate the HospitalDTO
            for (ProfileResponseDTO profile : profileResponseDTOS) {
                if (profile == null) {
                    continue; // Skip null profiles
                }

                // Create or retrieve the department
                if (profile.getDeptId() != null) {
                    DepartmentDTO departmentDTO = departmentMap.get(profile.getDeptId());
                    if (departmentDTO == null) {
                        departmentDTO = new DepartmentDTO();
                        departmentDTO.setDeptId(profile.getDeptId());
                        departmentDTO.setDeptName(profile.getDeptName());
                        departmentDTO.setDoctors(new ArrayList<>()); // Initialize the list of doctors
                        departmentMap.put(profile.getDeptId(), departmentDTO);
                    }

                    // Create the doctor if available
                    if (profile.getDocId() != null) {
                        DoctorDTO doctorDTO = new DoctorDTO();
                        doctorDTO.setDocId(profile.getDocId());
                        doctorDTO.setDocName(profile.getDocName());
                        doctorDTO.setDocEducation(profile.getDocEducation());
                        doctorDTO.setDocExperience(profile.getDocExperience());
                        doctorDTO.setDocBio(profile.getDocBio());

                        // Add the doctor to the department's doctor list
                        departmentDTO.getDoctors().add(doctorDTO);
                    }
                }
            }

            // Add all departments to the hospital DTO
            List<DepartmentDTO> departments = new ArrayList<>(departmentMap.values());
            hospitalDTO.setDepartments(departments.isEmpty() ?  new ArrayList<>() : departments);

            return new ResponseEntity<>(hospitalDTO, HttpStatus.OK);

        } catch (Exception e) {
            // Log the error and return a meaningful response
//            c("Error fetching hospital profile", e);
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setResponseMessage(e.getMessage());
            return new ResponseEntity<>(hospitalDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /**
     * Updates the Hospital Details.
     * @param hospitalUpdateInputParams input data for update
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity updateHospitalDetails(HospitalUpdateInputParams hospitalUpdateInputParams) {
        ResponseMessage responseMessage = new ResponseMessage();
        Hospital hospital = hospitalRepository.findByUserId(hospitalUpdateInputParams.getUserId());
        hospital.setName(hospitalUpdateInputParams.getHospitalName());
        hospital.setEmailId(hospitalUpdateInputParams.getEmailId());
        hospital.setAddress(hospitalUpdateInputParams.getAddress());
        hospital.setOrgType(hospitalUpdateInputParams.getOrgType());
        hospital.setRegdNo(hospitalUpdateInputParams.getRegdNo());
        hospital.setContactNo(hospitalUpdateInputParams.getContactNo());
        hospitalRepository.save(hospital);
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

    /**
     * Inserts new doctor.
     * @param doctorInputParams input doctor details
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity addDoctor(DoctorInputParams doctorInputParams) {
        Doctor doctor = new Doctor();
        ResponseMessage responseMessage = new ResponseMessage();
        doctor.setDeptId(doctorInputParams.getDeptId());
        doctor.setDocName(doctorInputParams.getDocName());
        doctor.setDocEducation(doctorInputParams.getDocEducation());
        doctor.setDocExperience(doctorInputParams.getDocExperience());
        doctor.setDocBio(doctorInputParams.getDocBio());
        Doctor doctorResponse = doctorRepository.save(doctor);
        responseMessage.setResponseMessage("Doctor added successfully with Doctor ID "+ doctorResponse.getDocId());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /**
     * Edit a doctor.
     * @param doctorInputParams input doctor details
     * @return response messages as a ResponseEntity
     */
    public ResponseEntity editDoctor(DoctorInputParams doctorInputParams) {
        Doctor doctor = doctorRepository.findByDocId(doctorInputParams.getDocId());
        ResponseMessage responseMessage = new ResponseMessage();
        doctor.setDocName(doctorInputParams.getDocName());
        doctor.setDocEducation(doctorInputParams.getDocEducation());
        doctor.setDocExperience(doctorInputParams.getDocExperience());
        doctor.setDocBio(doctorInputParams.getDocBio());
        Doctor doctorResponse = doctorRepository.save(doctor);
        responseMessage.setResponseMessage("Doctor edited successfully with Doctor ID "+ doctorResponse.getDocId());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
