package com.one.care.repository;

import com.one.care.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Contains database methods for patient profile.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    /**
     * Fetches patient details by id.
     * @param userId input id
     */
    Patient findByUserId(String userId);

    /**
     * Fetches the hospital details, department details and doctor details based upon userId.
     * @param userId input userId
     */
//    @Query("SELECT new com.one.care.dto.ProfileResponseDTO(h.userId, h.name, h.emailId, h.address, h.orgType, h.regdNo, h.contactNo, " +
//            "d.deptId, d.deptName, doc.docId, doc.docName, doc.docEducation, doc.docExperience, doc.docBio) " +
//            "FROM Hospital h " +
//            "LEFT JOIN Department d ON h.userId = d.hospitalId " +
//            "LEFT JOIN Doctor doc ON d.deptId = doc.deptId " +
//            "WHERE h.userId = :userId")
//    List<ProfileResponseDTO> findHospitalWithDepartmentsAndDoctors(String userId);
}
