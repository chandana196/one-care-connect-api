package com.one.care.repository;

import com.one.care.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Contains database methods for department profile.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
