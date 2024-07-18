package com.example.HealthCareApplication.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonIgnoreProperties()
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctorss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctor_id;

    @NotEmpty
    private String doctor_name;

    @NotEmpty
    @Past
    private LocalDate dob;

    @NotEmpty
    private String specialization;

    @NotEmpty
    private String sex;

    @NotEmpty
    private String mobile_no;

//    @NotEmpty
//    private String address;

    @NotEmpty
    private String email;

    @NotEmpty
    private String login_password;
}
