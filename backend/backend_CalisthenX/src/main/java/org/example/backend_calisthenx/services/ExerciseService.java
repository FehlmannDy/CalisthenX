package org.example.backend_calisthenx.services;

import jakarta.validation.Valid;
import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Exercise;
import org.example.backend_calisthenx.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Vérifier si l'exercice existe
        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise with id " + id + " not found"));

        // Mettre à jour les champs nécessaires
        existingExercise.setName(exerciseDetails.getName());
        existingExercise.setDescription(exerciseDetails.getDescription());
        existingExercise.setImageUrl(exerciseDetails.getImageUrl());
        existingExercise.setMuscleGroups(exerciseDetails.getMuscleGroups());

        // Sauvegarder et retourner l'exercice mis à jour
        return exerciseRepository.save(existingExercise);
    }
}
