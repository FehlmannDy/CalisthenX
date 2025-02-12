package org.example.backend_calisthenx.controllers;

import org.example.backend_calisthenx.models.ExerciseMuscleGroup;
import org.example.backend_calisthenx.services.ExerciseMuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<ExerciseMuscleGroup> getAllExerciseMuscleGroups() {
        return exerciseMuscleGroupService.getAllExerciseMuscleGroups();
    }

    @GetMapping("/exercise/{exerciseId}")
    public List<ExerciseMuscleGroup> getExerciseMuscleGroupsByExercise(@PathVariable Long exerciseId) {
        return exerciseMuscleGroupService.getExerciseMuscleGroupsByExercise(exerciseId);
    }

    @GetMapping("/muscleGroup/{muscleGroupId}")
    public List<ExerciseMuscleGroup> getExerciseMuscleGroupsByMuscleGroup(@PathVariable Long muscleGroupId) {
        return exerciseMuscleGroupService.getExerciseMuscleGroupsByMuscleGroup(muscleGroupId);
    }

    @PostMapping
    public ExerciseMuscleGroup createExerciseMuscleGroup(@RequestBody ExerciseMuscleGroup exerciseMuscleGroup) {
        return exerciseMuscleGroupService.saveExerciseMuscleGroup(exerciseMuscleGroup);
    }

    @PutMapping
    public ExerciseMuscleGroup updateExerciseMuscleGroup(@RequestBody ExerciseMuscleGroup exerciseMuscleGroup) {
        return exerciseMuscleGroupService.updateExerciseMuscleGroup(exerciseMuscleGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseMuscleGroup(@PathVariable Long id) {
        exerciseMuscleGroupService.deleteExerciseMuscleGroup(id);
        return ResponseEntity.noContent().build();
    }
}
