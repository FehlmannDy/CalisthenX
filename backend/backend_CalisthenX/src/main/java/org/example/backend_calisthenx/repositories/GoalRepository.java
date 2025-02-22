package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    Optional<Goal> findById(Long id);
    Optional<List<Goal>> findGoalsByAthlete(Athlete athlete);
}
