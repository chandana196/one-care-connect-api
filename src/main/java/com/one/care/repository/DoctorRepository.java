package com.one.care.repository;

import com.one.care.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains database methods for doctor profile.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findByDocId(int docId);
}
