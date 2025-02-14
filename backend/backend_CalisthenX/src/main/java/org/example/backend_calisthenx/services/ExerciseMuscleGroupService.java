package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.ExerciseMuscleGroup;
import org.example.backend_calisthenx.repositories.ExerciseMuscleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseMuscleGroupService {

    @Autowired
    private ExerciseMuscleGroupRepository exerciseMuscleGroupRepository;

    public List<ExerciseMuscleGroup> getAllExerciseMuscleGroups() {
        return exerciseMuscleGroupRepository.findAll();
    }

    public List<ExerciseMuscleGroup> getExerciseMuscleGroupsByExercise(Long exerciseId) {
        return exerciseMuscleGroupRepository.findByExerciseId(exerciseId);
    }

    public List<ExerciseMuscleGroup> getExerciseMuscleGroupsByMuscleGroup(Long muscleGroupId) {
        return exerciseMuscleGroupRepository.findByMuscleGroupId(muscleGroupId);
    }

    public ExerciseMuscleGroup saveExerciseMuscleGroup(ExerciseMuscleGroup exerciseMuscleGroup) {
        return exerciseMuscleGroupRepository.save(exerciseMuscleGroup);
    }

    public ExerciseMuscleGroup updateExerciseMuscleGroup(Long id,ExerciseMuscleGroup exerciseMuscleGroup) {
        ExerciseMuscleGroup existing = exerciseMuscleGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExerciseMuscleGroup not found with ID: " + id));

        existing.setExercise(exerciseMuscleGroup.getExercise());
        existing.setMuscleGroup(exerciseMuscleGroup.getMuscleGroup());

        return exerciseMuscleGroupRepository.save(existing);
    }

    public void deleteExerciseMuscleGroup(Long id) {
        if (!exerciseMuscleGroupRepository.existsById(id)) {
            throw new ResourceNotFoundException("ExerciseMuscleGroup with ID " + id + " not found");
        }
        exerciseMuscleGroupRepository.deleteById(id);
    }
}
