package org.example.backend_calisthenx.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.services.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/athletes")
@Validated
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    // Récupérer tous les athlètes
    @GetMapping
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        if (athletes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(athletes);
    }

    // Récupérer un athlète par email
    @GetMapping("/email")
    public ResponseEntity<Athlete> getByEmail(@RequestParam @Email String email) {
        Optional<Athlete> athlete = athleteService.getAthleteByEmail(email);
        return athlete.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Récupérer tous les athlètes coachés par un coach spécifique
    @GetMapping("/coached/{coachId}")
    public ResponseEntity<List<Athlete>> getAthleteByCoachId(@PathVariable Long coachId) {
        List<Athlete> athletes = athleteService.findByCoachId(coachId);
        if (athletes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(athletes);
    }

    // Créer un nouvel athlète
    @PostMapping("/save")
    public ResponseEntity<Athlete> createAthlete(@Valid @RequestBody Athlete athlete) {
        // Vérification préalable de l'existence de l'athlète (par email ou autre critère)
        Optional<Athlete> existingAthlete = athleteService.getAthleteByEmail(athlete.getEmail());
        if (existingAthlete.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null); // Retourne un code 409 si l'athlète existe déjà
        }

        athleteService.saveAthlete(athlete);
        return ResponseEntity.status(HttpStatus.CREATED).body(athlete);
    }
}
