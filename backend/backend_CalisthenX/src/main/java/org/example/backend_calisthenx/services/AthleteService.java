package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.repositories.AthleteRepository;
import org.example.backend_calisthenx.exceptions.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {
    private final AthleteRepository athleteRepository;

    @Autowired
    public AthleteService(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    public Optional<Athlete> getAthleteByEmail(String email) {
        return athleteRepository.findByEmail(email);
    }

    public List<Athlete> findByCoachId(Long coachId) {
        return athleteRepository.findByCoachId(coachId);
    }

    public Athlete saveAthlete(Athlete athlete) {
        if(athleteRepository.findByEmail(athlete.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email " + athlete.getEmail() + " is already used");
        }
        return athleteRepository.save(athlete);
    }

    public Optional<Athlete> getAthleteById(Long id) {
        return athleteRepository.findById(id);
    }
}

