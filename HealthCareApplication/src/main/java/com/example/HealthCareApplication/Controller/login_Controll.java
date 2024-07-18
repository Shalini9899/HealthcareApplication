package com.example.HealthCareApplication.Controller;

import com.example.HealthCareApplication.Entity.Doctorss;
import com.example.HealthCareApplication.Entity.Patients;
import com.example.HealthCareApplication.Repository.DoctorRepo;
import com.example.HealthCareApplication.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class login_Controll {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping({"/"})
    public String loggedIn(@AuthenticationPrincipal User user, Model model) {
        String username = user.getUsername();

        String url = "";
        if (doctorRepo.findByEmail(username) != null) {
            Doctorss doc = doctorRepo.findByEmail(username);
            long docId = doc.getDoctor_id();
            url = "redirect:/doctor/" + docId;
        } else if (patientRepo.findByEmail(username) != null) {
            Patients pat = patientRepo.findByEmail(username);
            int patId = pat.getPatient_id();
            url = "redirect:/patient/" + patId;
        }
        return url;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "redirect:/dashboard";
    }


}
