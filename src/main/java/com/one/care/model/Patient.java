package com.one.care.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity class for Hospital table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "patient" , schema = "one_care_connect")
public class Patient {

    @Id
    private String userId;
    private String name;
    private String fsName;
    private LocalDate dob;
    private String bGroup;
    private String aadhar;
    private String email;
    private String mobile;
    private String address;
}
