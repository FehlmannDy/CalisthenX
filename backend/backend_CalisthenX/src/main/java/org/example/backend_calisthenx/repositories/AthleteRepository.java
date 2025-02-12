package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByLastName(String lastName);  // Trouver par nom de famille
    Optional<Athlete> findByEmail(String email);    // Trouver un athlète par email
    List<Athlete> findByCoachId(Long coachId);      // Trouver tous les athlètes d'un coach
}

