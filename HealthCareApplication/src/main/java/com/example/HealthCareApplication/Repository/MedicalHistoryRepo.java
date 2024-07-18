package com.example.HealthCareApplication.Repository;


import com.example.HealthCareApplication.Entity.medicalhistory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Tag(name = "Medical History - Rest API Controllers", description = "Medical History API")
@RepositoryRestResource(collectionResourceRel = "medical_history", path = "medical_history")
public interface MedicalHistoryRepo extends JpaRepository<medicalhistory,Integer> {

    public List<medicalhistory> findByPatientId(@Param("id") int id);
}
