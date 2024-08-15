package com.one.care.repository;


import com.one.care.dto.ProfileResponseDTO;
import com.one.care.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Contains database methods for hospital profile.
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {

    /**
     * Fetches hospital details by userId.
     * @param userId input userId
     */
    Hospital findByUserId(String userId);

    /**
     * Fetches the hospital details, department details and doctor details based upon userId.
     * @param userId input userId
     */
//    @Query(value = "SELECT h.user_id, h.name, h.email_id, h.address, h.org_type, h.regd_no, h.contact_no, d.dept_id, d.dept_name, doc.doc_id, doc.doc_name, doc.doc_education, doc.doc_experience, doc.doc_bio " +
//            "FROM one_care_connect.hospital h " +
//            "JOIN one_care_connect.department d ON h.user_id = d.hospital_id " +
//            "LEFT JOIN one_care_connect.doctor doc ON d.dept_id = doc.dept_id " +
//            "WHERE h.user_id = :userId" , nativeQuery = true)

    @Query("SELECT h.userId, h.name, h.emailId, h.address, h.orgType, h.regdNo, h.contactNo, d.deptId, d.deptName, doc.docId, doc.docName, doc.docEducation, doc.docExperience, doc.docBio " +
            "FROM Hospital h " +
            "JOIN Department d ON h.userId = d.hospitalId " +
            "LEFT JOIN Doctor doc ON d.deptId = doc.deptId " +
            "WHERE h.userId = :userId")
    List<ProfileResponseDTO> findHospitalWithDepartmentsAndDoctors(String userId);

}
