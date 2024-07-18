package com.example.HealthCareApplication.Repository;


import com.example.HealthCareApplication.Entity.Doctorss;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Tag(name = "Record: Doctor - Rest API Controllers", description = "Doctor Record API")
@RepositoryRestResource(collectionResourceRel = "doctor", path = "doctor")
public interface DoctorRepo extends JpaRepository<Doctorss, Integer> {

    public Doctorss findByEmail(@Param("email") String email);
}
