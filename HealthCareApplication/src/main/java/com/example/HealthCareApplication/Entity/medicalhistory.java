package com.example.HealthCareApplication.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonIgnoreProperties()
@Entity(name = "medical_history")
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@NamedQuery(name = "Medical_History.PatientId", query = "from medical_history where patientId =:id")
public class medicalhistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int history_id;

    @NotEmpty
    @Column(name = "history_patient_id")
    private int patientId;

    @NotEmpty
    @PastOrPresent
    private LocalDate diagnosis_date;

    private String medical_condition;
}
