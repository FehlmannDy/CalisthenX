package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.exceptions.DuplicateResourceException;
import org.example.backend_calisthenx.exceptions.EmailAlreadyExistsException;
import org.example.backend_calisthenx.models.Coach;
import org.example.backend_calisthenx.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    private final CoachRepository coachRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CoachService(CoachRepository coachRepository, PasswordEncoder passwordEncoder) {
        this.coachRepository = coachRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoachById(Long id) {
        return coachRepository.findById(id);
    }

    public Optional<Coach> getByEmail(String email) {
        return coachRepository.findByEmail(email);
    }

    public Coach saveCoach(Coach coach) {
        if(coachRepository.findByEmail(coach.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email " + coach.getEmail() + " is already used");
        }
        if(coachRepository.findCoachByUsername(coach.getUsername()).isPresent()){
            throw new DuplicateResourceException("Username " + coach.getUsername() + " already exists");
        }
        coach.setCreatedAt(LocalDateTime.now());
        coach.setPassword(passwordEncoder.encode(coach.getPassword()));
        return coachRepository.save(coach);
    }
}
