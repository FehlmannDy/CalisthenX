package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.Exercise;
import org.example.backend_calisthenx.models.ExerciseRecord;
import org.example.backend_calisthenx.models.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {

    List<ExerciseRecord> findByTrainingHistory(TrainingHistory trainingHistory);

    List<ExerciseRecord> findByExercise(Exercise exercise);

}

