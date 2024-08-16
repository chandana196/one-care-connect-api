package com.one.care.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Entity class for Hospital table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vitals" , schema = "one_care_connect")
public class Vitals {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer vitalId;
    private String patientId;
    private String vitalType;
    private String vitalValue;
    private Timestamp vitalDate;
}
