package com.one.care.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "doctor" , schema = "one_care_connect")
public class Doctor {

    @Id
    private int docId;
    private String deptId;
    private String docName;
    private String docEducation;
    private String docExperience;
    private String docBio;
}
