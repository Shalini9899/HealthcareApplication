package com.example.HealthCareApplication.Configuration;


import com.example.HealthCareApplication.Entity.Doctorss;
import com.example.HealthCareApplication.Entity.Patients;
import com.example.HealthCareApplication.Repository.DoctorRepo;
import com.example.HealthCareApplication.Repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PatientRepo patientRepo;
@Bean
public UserDetailsService userDetailsService(PasswordEncoder encoder) {

    List<Doctorss> docs = doctorRepo.findAll();
    List<Patients> pats = patientRepo.findAll();

    List<UserDetails> doctors = new ArrayList<>();
    List<UserDetails> patients = new ArrayList<>();

    for (Doctorss doc : docs) {
        doctors.add(User.withUsername(doc.getEmail())
                .password(encoder.encode(doc.getLogin_password()))
                .roles("USER")
                .build());
    }

    for (Patients pat : pats) {
        patients.add(User.withUsername(pat.getEmail())
                .password(encoder.encode(pat.getLogin_password()))
                .roles("USER")
                .build());
    }

    Collection<UserDetails> allUsers = new ArrayList<>();
    allUsers.addAll(doctors);
    allUsers.addAll(patients);
    return new InMemoryUserDetailsManager(allUsers);
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .build();
    }
}
