package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.models.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Long> {
    List<TrainingHistory> findByAthlete(Athlete athlete);
}
