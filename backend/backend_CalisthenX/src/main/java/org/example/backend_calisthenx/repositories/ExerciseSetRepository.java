package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {

    List<ExerciseSet> findByExerciseRecordId(Long exerciseRecordId);

    List<ExerciseSet> findByExerciseRecordIdAndSetNumber(Long exerciseRecordId, Integer setNumber);

    ExerciseSet findByIdAndExerciseRecordId(Long id, Long exerciseRecordId);
}
