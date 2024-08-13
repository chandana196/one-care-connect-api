package com.one.care.dto;

import lombok.Data;

/**
 * Contains Input params for hospital profile update.
 */
@Data
public class HospitalUpdateInputParams {
    private String userId;
    private String hospitalName;
    private String emailId;
    private String address;
    private String orgType;
    private String regdNo;
    private String contactNo;
}
