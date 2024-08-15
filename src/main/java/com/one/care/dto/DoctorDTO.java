package com.one.care.dto;

import lombok.Data;

/**
 * DTO class for Doctor.
 */
@Data
public class DoctorDTO {
    private int docId;
    private String docName;
    private String docEducation;
    private int docExperience;
    private String docBio;
}