package com.one.care.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Entity class for Disease table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "disease" , schema = "one_care_connect")
public class Disease {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer diseaseId;
    private String patientId;
    private String diseaseName;
    private String doctorName;
    private String doctorComments;
    private Boolean isIpd;
    private String hospitalName;
    private LocalDate diseaseDate;
    private LocalDate dischargeDate;
}
