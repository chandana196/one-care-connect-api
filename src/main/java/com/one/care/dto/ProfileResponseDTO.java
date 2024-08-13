package com.one.care.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for profile of Hospital along with doctor and department.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponseDTO {
    private String userId;
    private String name;
    private String emailId;
    private String address;
    private String orgType;
    private String regdNo;
    private String contactNo;
    private int deptId;
    private String deptName;
    private int docId;
    private String docName;
    private String docEducation;
    private int docExperience;
    private String docBio;

}
