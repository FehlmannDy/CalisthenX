package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.models.Coach;
import org.example.backend_calisthenx.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    private final CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
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
        return coachRepository.save(coach);
    }
}
