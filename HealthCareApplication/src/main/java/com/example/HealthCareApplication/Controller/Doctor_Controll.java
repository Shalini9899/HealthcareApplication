package com.example.HealthCareApplication.Controller;


import com.example.HealthCareApplication.Entity.Appointmentss;
import com.example.HealthCareApplication.Entity.Patients;
import com.example.HealthCareApplication.Entity.Prescriptionss;
import com.example.HealthCareApplication.Entity.medicalhistory;
import com.example.HealthCareApplication.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("doctor")
public class Doctor_Controll {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    MedicalHistoryRepo medicalHistoryRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    PrescriptionRepo prescriptionRepo;


    @GetMapping("{docId}")
    public String welcome(@PathVariable("docId") int docId, Model model) {
        String name = doctorRepo.findById(docId).get().getDoctor_name();
        model.addAttribute("id", docId);
        model.addAttribute("name", name);
        return "doctor_welcome";
    }


    @GetMapping("checkappointment/{docId}")
    public String getAppointmentCheck(@PathVariable("docId") int docId, Model model) {
        String name = doctorRepo.findById(docId).get().getDoctor_name();
        model.addAttribute("id", docId);
        model.addAttribute("name", name);

        List<Appointmentss> appointments = appointmentRepo.findByDoctorId(docId);
        model.addAttribute("appointments", appointments);

        return "doctor_appointment_check";
    }


    @GetMapping("cancelappointment/{appId}")
    public String getAppointmentCancel(@PathVariable("appId") int appId, Model model) {
        Appointmentss appointment = appointmentRepo.findById(appId).get();
        model.addAttribute("appointment", appointment);

        return "doctor_appointment_cancel";
    }

    @PostMapping("cancelappointment/{appId}")
    public String postAppointmentCancel(@PathVariable("appId") int appId) {
        int docId = appointmentRepo.findById(appId).get().getDoctorId();
        appointmentRepo.deleteById(appId);

        return "redirect:/doctor/" + docId;
    }

    @GetMapping("issueprescription/{appId}")
    public String getPrescriptionIssue(@PathVariable("appId") int appId, Model model) {
        Appointmentss app = appointmentRepo.findById(appId).get();
        Prescriptionss prescription = new Prescriptionss();

        prescription.setDoctorId(app.getDoctorId());
        prescription.setPatientId(app.getPatientId());
        prescription.setIssuedDateTime(LocalDateTime.now());
        model.addAttribute("prescriptionForm", prescription);

        int patientId = app.getPatientId();
        String name = patientRepo.findById(patientId).get().getPatient_name();
        model.addAttribute("id", patientId);
        model.addAttribute("name", name);

        return "doctor_prescription_issue";
    }

    @PostMapping("issueprescription/save")
    public String postPrescriptionIssue(@ModelAttribute Prescriptionss prescription) {
        prescriptionRepo.save(prescription);
        return "redirect:/doctor/" + prescription.getDoctorId();
    }

    @GetMapping("issuedprescription/{docId}")
    public String getPrescriptionIssued(@PathVariable("docId") int docId, Model model) {
        String name = doctorRepo.findById(docId).get().getDoctor_name();
        model.addAttribute("id", docId);
        model.addAttribute("name", name);

        List<Prescriptionss> prescriptions = prescriptionRepo.findByDoctorId(docId);
        model.addAttribute("prescriptions", prescriptions);

        return "doctor_prescription_check";
    }

    @GetMapping("modifyprescription/{prescId}")
    public String getPrescriptionModify(@PathVariable("prescId") int prescId, Model model) {
        int patientId = prescriptionRepo.findById(prescId).get().getPatientId();
        String name = patientRepo.findById(patientId).get().getPatient_name();
        model.addAttribute("id", patientId);
        model.addAttribute("name", name);

        Prescriptionss prescription = prescriptionRepo.findById(prescId).get();
        prescription.setPrescription_id(prescId);
        prescription.setIssuedDateTime(LocalDateTime.now());
        model.addAttribute("modifyPrescriptionForm", prescription);

        return "doctor_prescription_modify";
    }

    @PostMapping("modifyprescription/save")
    public String postPrescriptionModify(@ModelAttribute("prescription") Prescriptionss prescription) {
        prescriptionRepo.save(prescription);

        return "redirect:/doctor/" + prescription.getDoctorId();
    }

    @GetMapping("deleteprescription/{prescId}")
    public String getPrescriptionRemove(@PathVariable("prescId") int prescId, Model model) {
        model.addAttribute("prescription", prescriptionRepo.findById(prescId).get());

        return "doctor_prescription_remove";
    }

    @PostMapping("deleteprescription/{prescId}")
    public String postPrescriptionRemove(@PathVariable("prescId") int prescId) {
        int docId = prescriptionRepo.findById(prescId).get().getDoctorId();
        prescriptionRepo.deleteById(prescId);

        return "redirect:/doctor/" + docId;
    }

    @GetMapping("medicalhistory/{patientId}")
    public String getDocMedicalHistory(@PathVariable("patientId") int patientId, Model model) {
        Patients patient = patientRepo.findById(patientId).get();
        model.addAttribute("patient", patient);

        List<medicalhistory> medHistory = medicalHistoryRepo.findByPatientId(patientId);
        model.addAttribute("history", medHistory);

        return "Medical_history";
    }

    @GetMapping("updatemedicalhistory/{appId}")
    public String getMedicalHistory(@PathVariable("appId") int appId, Model model) {
        String name = patientRepo.findById(appId).get().getPatient_name();

        model.addAttribute("id", appId);
        model.addAttribute("name", name);

        medicalhistory hist = new medicalhistory();
        hist.setPatientId(appId);
        model.addAttribute("history", hist);

        return "doctor_medical_history";
    }

    @PostMapping("updatemedicalhistory/save")
    public String updateMedicalHistory(@ModelAttribute medicalhistory medHis) {
        medHis.setDiagnosis_date(LocalDate.now());
        medicalHistoryRepo.save(medHis);

        int patientId = medHis.getPatientId();
        return "redirect:/doctor/" + patientId;
    }
}
