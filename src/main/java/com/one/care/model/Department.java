package com.one.care.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for Department table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "department" , schema = "one_care_connect")
public class Department {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int deptId;
    private String deptName;
    private String hospitalId;
}
