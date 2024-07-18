package com.example.HealthCareApplication.Repository;


import com.example.HealthCareApplication.Entity.Patients;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Tag(name = "Record: Patient - Rest API Controllers", description = "Patient Record API")
@RepositoryRestResource(collectionResourceRel = "patient", path="patient")
public interface PatientRepo extends JpaRepository<Patients,Integer> {

    public Patients findByEmail(@Param("email") String email);

}
