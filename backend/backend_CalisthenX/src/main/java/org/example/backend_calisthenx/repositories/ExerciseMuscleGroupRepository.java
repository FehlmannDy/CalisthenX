package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.ExerciseMuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseMuscleGroupRepository extends JpaRepository<ExerciseMuscleGroup, Long> {

    List<ExerciseMuscleGroup> findByExerciseId(Long exerciseId);

    List<ExerciseMuscleGroup> findByMuscleGroupId(Long muscleGroupId);

}
