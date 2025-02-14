package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    Optional<Athlete> findByEmail(String email);
    List<Athlete> findByCoachId(Long coachId);

    Optional<Athlete> findAthleteByUsername(String username);
}

