package com.example.HealthCareApplication.Repository;


import com.example.HealthCareApplication.Entity.Appointmentss;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Tag(name = "Appointment History - Rest API Controllers", description = "Appointment API")
@RepositoryRestResource(collectionResourceRel = "appointment", path = "appointment")
public interface AppointmentRepo extends JpaRepository<Appointmentss,Integer> {


    public List<Appointmentss> findByPatientId(@Param("id") int id);

    public List<Appointmentss> findByDoctorId(@Param("id") int id);

}
