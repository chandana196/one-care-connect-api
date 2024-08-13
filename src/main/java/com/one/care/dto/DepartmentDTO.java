package com.one.care.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO class for Department.
 */
@Data
public class DepartmentDTO {
    private int deptId;
    private String deptName;
    private List<DoctorDTO> doctors;
}
