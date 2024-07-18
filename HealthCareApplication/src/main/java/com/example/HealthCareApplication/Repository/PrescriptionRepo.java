package com.example.HealthCareApplication.Repository;


import com.example.HealthCareApplication.Entity.Prescriptionss;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Tag(name = "Prescription History - Rest API Controllers", description = "Prescription API")
@RepositoryRestResource(collectionResourceRel = "prescription", path = "prescription")
public interface PrescriptionRepo extends JpaRepository<Prescriptionss,Integer> {
    public List<Prescriptionss> findByPatientId(@Param("id") int id);

    public List<Prescriptionss> findByDoctorId(@Param("id") int id);
}
