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
@Table(name = "department" , schema = "one_care_connect")
public class Department {

    @Id
    private int deptId;
    private String deptName;
    private String hospitalId;
}
