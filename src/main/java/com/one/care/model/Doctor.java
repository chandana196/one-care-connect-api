package com.one.care.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for Doctor table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "doctor" , schema = "one_care_connect")
public class Doctor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int docId;
    private int deptId;
    private String docName;
    private String docEducation;
    private int docExperience;
    private String docBio;
}
