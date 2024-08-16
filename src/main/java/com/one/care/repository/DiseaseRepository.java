package com.one.care.repository;

import com.one.care.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Contains database methods for Disease
 */
@Repository
public interface DiseaseRepository extends JpaRepository<Disease, String> {

    /**
     * Fetches disease details by id.
     * @param patientId input id
     */
    List<Disease> findByPatientId(String patientId);


    Disease findByDiseaseId(int diseaseId);
}
