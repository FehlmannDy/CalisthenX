package org.example.backend_calisthenx.controllers;

import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.ExerciseMuscleGroup;
import org.example.backend_calisthenx.services.ExerciseMuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@RestController
@RequestMapping("/api/exerciseMuscleGroups")
public class ExerciseMuscleGroupController {

    private final ExerciseMuscleGroupService exerciseMuscleGroupService;

    @Autowired
    public ExerciseMuscleGroupController(ExerciseMuscleGroupService exerciseMuscleGroupService) {
        this.exerciseMuscleGroupService = exerciseMuscleGroupService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseMuscleGroup>> getAllExerciseMuscleGroups() {
        List<ExerciseMuscleGroup> exerciseMuscleGroups = exerciseMuscleGroupService.getAllExerciseMuscleGroups();
        if (exerciseMuscleGroups.isEmpty()) {
            throw new ResourceNotFoundException("No exercise muscle groups found");
        }
        return ResponseEntity.ok(exerciseMuscleGroups);
    }

    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<List<ExerciseMuscleGroup>> getExerciseMuscleGroupsByExercise(@PathVariable Long exerciseId) {
        List<ExerciseMuscleGroup> exerciseMuscleGroups = exerciseMuscleGroupService.getExerciseMuscleGroupsByExercise(exerciseId);
        if (exerciseMuscleGroups.isEmpty()) {
            throw new ResourceNotFoundException("No exercise muscle groups found for this exercise id : " + exerciseId);
        }
        return ResponseEntity.ok(exerciseMuscleGroups);
    }

    @GetMapping("/muscleGroup/{muscleGroupId}")
    public ResponseEntity<List<ExerciseMuscleGroup>> getExerciseMuscleGroupsByMuscleGroup(@PathVariable Long muscleGroupId) {
        List<ExerciseMuscleGroup> exerciseMuscleGroups = exerciseMuscleGroupService.getExerciseMuscleGroupsByMuscleGroup(muscleGroupId);
        if (exerciseMuscleGroups.isEmpty()) {
            throw new ResourceNotFoundException("No exerise muscle group found for this muscle group id : " + muscleGroupId);
        }
        return ResponseEntity.ok(exerciseMuscleGroups);
    }

    @PostMapping
    public ResponseEntity<ExerciseMuscleGroup> createExerciseMuscleGroup(@RequestBody ExerciseMuscleGroup exerciseMuscleGroup) {
        ExerciseMuscleGroup created = exerciseMuscleGroupService.saveExerciseMuscleGroup(exerciseMuscleGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseMuscleGroup> updateExerciseMuscleGroup(@ PathVariable Long id ,@RequestBody ExerciseMuscleGroup exerciseMuscleGroup) {
        ExerciseMuscleGroup updated = exerciseMuscleGroupService.updateExerciseMuscleGroup(id, exerciseMuscleGroup);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseMuscleGroup(@PathVariable Long id) {
        exerciseMuscleGroupService.deleteExerciseMuscleGroup(id);
        return ResponseEntity.noContent().build();
    }
}
