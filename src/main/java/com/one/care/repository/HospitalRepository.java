package com.one.care.repository;


import com.one.care.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Contains database methods for hospital profile.
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {

    /**
     *
     * @param userId
     * @return
     */
    Hospital findByUserId(String userId);

    @Query("SELECT h.userId, h.name, h.emailId, h.address, h.orgType, h.regdNo, h.contactNo, d.dept_id, d.dept_name, doc.doc_id, doc.doc_name, doc.doc_education, doc.doc_experience, doc.doc_bio " +
            "FROM hospital h " +
            "JOIN department d ON h.userId = d.hospital_id " +
            "LEFT JOIN doctor doc ON d.dept_id = doc.dept_id " +
            "WHERE h.userId = :userId")
    Hospital findHospitalWithDepartmentsAndDoctors(String userId);
}
