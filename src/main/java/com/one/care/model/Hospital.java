package com.one.care.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for Hospital table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "hospital" , schema = "one_care_connect")
public class Hospital {

    @Id
    private String userId;
    private String name;
    private String emailId;
    private String address;
    private String orgType;
    private String regdNo;
    private String password;
    private String contactNo;
}
