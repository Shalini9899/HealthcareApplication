package com.example.HealthCareApplication.Controller;


import com.example.HealthCareApplication.Entity.*;
import com.example.HealthCareApplication.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("patient")
public class Patient_Controll {


    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    MedicalHistoryRepo medicalHistoryRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    PrescriptionRepo prescriptionRepo;

    @GetMapping({ "{patientId}" })
    public String welcome(@PathVariable("patientId") int patientId, Model model) {
        String name = patientRepo.findById(patientId).get().getPatient_name();
        model.addAttribute("id", patientId);
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping({ "doctordetails" })
    public String getDoctorDetails(Model model) {
        List<Doctorss> doctors = doctorRepo.findAll();
        model.addAttribute("doctors", doctors);
        return "doctor_details";
    }
    @GetMapping({ "bookappointment/{patientId}" })
    public String getPatientAppointmentBook(@PathVariable("patientId") int patientId, Model model) {
        Appointmentss appointment = new Appointmentss();
        appointment.setPatientId(patientId);

        Patients patient = patientRepo.findById(patientId).get();

        List<Doctorss> doctors = doctorRepo.findAll();

        model.addAttribute("appointmentForm", appointment);
        model.addAttribute("doctors", doctors);
        model.addAttribute("patient", patient);
        return "appointment_book";
    }

    @PostMapping("/bookappointment/save")
    public String postAppointmentBook(@ModelAttribute Appointmentss appointment, Model model) {
        appointmentRepo.save(appointment);
        int id = appointment.getPatientId();
        return "redirect:/patient/" + id;
    }

    @GetMapping({"checkappointment/{patientId}" })
    public String getAppointmentCheck(@PathVariable("patientId") int patientId, Model model) {

        List<Appointmentss> appointments = appointmentRepo.findByPatientId(patientId);
        model.addAttribute("appointments", appointments);
        String name = patientRepo.findById(patientId).get().getPatient_name();
        model.addAttribute("id", patientId);
        model.addAttribute("name", name);
        return "appointment_check";
    }
    @GetMapping({ "checkprescription/{patientId}" })
    public String getPrescriptionCheck(@PathVariable("patientId") int patientId, Model model) {
        List<Prescriptionss> prescriptions = prescriptionRepo.findByPatientId(patientId);
        model.addAttribute("prescriptions", prescriptions);

        String name = patientRepo.findById(patientId).get().getPatient_name();
        model.addAttribute("id", patientId);
        model.addAttribute("name", name);
        return "prescription_check";
    }
    @GetMapping({ "medicalhistory/{patientId}" })
    public String getMedicalHistory(@PathVariable("patientId") int patientId, Model model) {
        model.addAttribute("patient", patientRepo.findById(patientId).get());

        List<medicalhistory> medHistory = medicalHistoryRepo.findByPatientId(patientId);
        model.addAttribute("history", medHistory);

        return "Medical_history";
    }
}
