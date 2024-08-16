package com.one.care.repository;

import com.one.care.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Contains database methods for vitals
 */
@Repository
public interface VitalsRepository extends JpaRepository<Vitals, String> {

    /**
     * Fetches vital details by id.
     * @param patientId input id
     */
    List<Vitals> findByPatientId(String patientId);


    Vitals findByVitalId(int vitalId);
//    Vitals findByVi(String userId);

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
