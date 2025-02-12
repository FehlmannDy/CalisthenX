package org.example.backend_calisthenx.controllers;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.services.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/athletes")
@Validated
public class AthleteController {
    @Autowired
    private AthleteService athleteService;

    @GetMapping
    public List<Athlete> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    @GetMapping("/email")
    public Optional<Athlete> get(@RequestParam @Email String email) {
        return athleteService.getAthleteByEmail(email);
    }

    @GetMapping("/coached/{coachId}")
    public List<Athlete> getAthleteByCoachId(@PathVariable Long coachId) {
        return athleteService.findByCoachId(coachId);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createAthlete(@Valid  @RequestBody Athlete athlete) {
        athleteService.saveAthlete(athlete);
        return ResponseEntity.status(HttpStatus.CREATED).body(athlete);
    }

}
