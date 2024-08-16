package com.one.care.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * DTO class for Doctor.
 */
@Data
public class VitalsDTO {
    private int vitalId;
    private String patientId;
    private String vitalType;
    private String vitalValue;
    private Timestamp vitalDate;
}