package com.one.care.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO class for hospital
 */
@Data
public class HospitalDTO {
    private String hospitalId;
    private String hospitalName;
    private List<DepartmentDTO> departments;
}
