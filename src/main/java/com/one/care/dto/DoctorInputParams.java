package com.one.care.dto;

import lombok.Data;

/**
 *
 */
@Data
public class DoctorInputParams {
    private int deptId;
    private int docId;
    private String docName;
    private String docEducation;
    private int docExperience;
    private String docBio;
}
