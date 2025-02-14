package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.exceptions.DuplicateResourceException;
import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.models.Coach;
import org.example.backend_calisthenx.repositories.AthleteRepository;
import org.example.backend_calisthenx.exceptions.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final CoachService coachService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AthleteService(AthleteRepository athleteRepository, CoachService coachService, PasswordEncoder passwordEncoder) {
        this.athleteRepository = athleteRepository;
        this.coachService = coachService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    public Optional<Athlete> getAthleteByEmail(String email) {
        return athleteRepository.findByEmail(email);
    }

    public Optional<Athlete> getAthleteById(long id) {
        return athleteRepository.findById(id);
    }

    public List<Athlete> findByCoachId(Long coachId) {
        return athleteRepository.findByCoachId(coachId);
    }

    public Athlete saveAthlete(Athlete athlete) {
        // Vérifier si l'email est déjà utilisé
        if (athleteRepository.findByEmail(athlete.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email '" + athlete.getEmail() + "' is already used");
        }

        // Vérifier si le nom d'utilisateur est déjà utilisé
        if (athleteRepository.findAthleteByUsername(athlete.getUsername()).isPresent()) {
            throw new DuplicateResourceException("Username '" + athlete.getUsername() + "' is already used");
        }

        // Vérifier si le coach existe
        Coach coach = coachService.getCoachById(athlete.getCoach().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Coach with ID " + athlete.getCoach().getId() + " not found"));

        // Assigner le coach validé
        athlete.setCoach(coach);

        // Hacher le mot de passe avant d'enregistrer
        athlete.setPassword(passwordEncoder.encode(athlete.getPassword()));

        // Définir la date de création
        athlete.setCreatedAt(LocalDateTime.now());

        // Sauvegarde et retour
        return athleteRepository.save(athlete);
    }

    public Optional<Athlete> getAthleteById(Long id) {
        return athleteRepository.findById(id);
    }
}

