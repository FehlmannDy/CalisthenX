package org.example.backend_calisthenx.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.example.backend_calisthenx.exceptions.DuplicateResourceException;
import org.example.backend_calisthenx.exceptions.GlobalExceptionHandler;
import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.models.Coach;
import org.example.backend_calisthenx.services.AthleteService;
import org.example.backend_calisthenx.services.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/athletes")
@Validated
public class AthleteController {

    private final AthleteService athleteService;
    private final PasswordEncoder passwordEncoder;
    private final CoachService coachService;
    private final GlobalExceptionHandler globalExceptionHandler;

    public AthleteController(AthleteService athleteService, PasswordEncoder passwordEncoder, CoachService coachService, GlobalExceptionHandler globalExceptionHandler) {
        this.athleteService = athleteService;
        this.passwordEncoder = passwordEncoder;
        this.coachService = coachService;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    // Récupérer tous les athlètes
    @GetMapping
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        if (athletes.isEmpty()) {
            throw new ResourceNotFoundException("Athletes not found");
        }
        return ResponseEntity.ok(athletes);
    }

    // Récupérer un athlète par email
    @GetMapping("/email")
    public ResponseEntity<Athlete> getByEmail(@RequestParam @Email String email) {
        return athleteService.getAthleteByEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Athlete with email " + email + " not found"));
    }

    // Récupérer tous les athlètes coachés par un coach spécifique
    @GetMapping("/coached/{coachId}")
    public ResponseEntity<List<Athlete>> getAthleteByCoachId(@PathVariable Long coachId) {
        List<Athlete> athletes = athleteService.findByCoachId(coachId);
        if (athletes.isEmpty()) {
            throw new ResourceNotFoundException("No athletes found for coach with id " + coachId);
        }
        return ResponseEntity.ok(athletes);
    }

    // Créer un nouvel athlète
    @PostMapping("/save")
    public ResponseEntity<Athlete> createAthlete(@Valid @RequestBody Athlete athlete) {
        Athlete savedAthlete = athleteService.saveAthlete(athlete);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAthlete);
    }
}
