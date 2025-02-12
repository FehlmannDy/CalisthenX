package org.example.backend_calisthenx.services;

import jakarta.validation.Valid;
import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Exercise;
import org.example.backend_calisthenx.models.ExerciseRecord;
import org.example.backend_calisthenx.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }


    public Exercise findById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    public Exercise save(@Valid Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteById(Long id) {
        exerciseRepository.deleteById(id);
    }

    public Exercise updateExercise(Long id, @Valid Exercise exerciseDetails) {
        Exercise existingExercise = getExerciseById(id);
        if (existingExercise != null) {
            throw new ResourceNotFoundException("Exercise not found");
        }

        existingExercise.setName(exerciseDetails.getName());
        existingExercise.setDescription(exerciseDetails.getDescription());
        existingExercise.setImageUrl(exerciseDetails.getImageUrl());
        existingExercise.setMuscleGroups(exerciseDetails.getMuscleGroups());

        Exercise updatedExercise = exerciseRepository.save(existingExercise);
        return updatedExercise;
    }


    private Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }
}
