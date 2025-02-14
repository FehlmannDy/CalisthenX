package org.example.backend_calisthenx.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.example.backend_calisthenx.exceptions.DuplicateResourceException;
import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Coach;
import org.example.backend_calisthenx.services.AthleteService;
import org.example.backend_calisthenx.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@Validated
public class CoachController {

    @Autowired
    private CoachService coachService;
    @Autowired
    private AthleteService athleteService;

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoaches() {
        List<Coach> coaches = coachService.getAllCoaches();
        if (coaches.isEmpty()) {
            throw new ResourceNotFoundException("Coaches not found");
        }
        return ResponseEntity.ok(coaches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        return coachService.getCoachById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Coach not found with ID: " + id));
    }

    @GetMapping("/email")
    public ResponseEntity<Coach> getCoachByEmail(@RequestParam @Email String email) {
        return coachService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Coach not found with email: " + email));
    }

    @PostMapping
    public ResponseEntity<Coach> saveCoach(@Valid @RequestBody Coach coach) {
        Coach savedCoach = coachService.saveCoach(coach);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoach);
    }
}



