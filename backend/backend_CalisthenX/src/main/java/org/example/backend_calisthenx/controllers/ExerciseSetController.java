package org.example.backend_calisthenx.controllers;

import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.ExerciseSet;
import org.example.backend_calisthenx.services.ExerciseSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exerciseSets")
public class ExerciseSetController {

    @Autowired
    private ExerciseSetService exerciseSetService;

    @GetMapping("/exerciseRecord/{exerciseRecordId}")
    public ResponseEntity<List<ExerciseSet>> getExerciseSetsByExerciseRecord(@PathVariable Long exerciseRecordId) {
        List<ExerciseSet> exerciseSets = exerciseSetService.getExerciseSetsByExerciseRecord(exerciseRecordId);
        if (exerciseSets.isEmpty()) {
            throw new ResourceNotFoundException("Exercise Set not found");
        }
        return ResponseEntity.ok(exerciseSets);
    }

    @PostMapping
    public ResponseEntity<ExerciseSet> createExerciseSet(@RequestBody ExerciseSet exerciseSet) {
        ExerciseSet created = exerciseSetService.createExerciseSet(exerciseSet);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    public ResponseEntity<ExerciseSet> updateExerciseSet(@RequestBody ExerciseSet exerciseSet) {
        ExerciseSet updated = exerciseSetService.updateExerciseSet(exerciseSet);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}/exerciseRecord/{exerciseRecordId}")
    public ResponseEntity<Void> deleteExerciseSet(@PathVariable Long id, @PathVariable Long exerciseRecordId) {
        exerciseSetService.deleteExerciseSet(id, exerciseRecordId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/exerciseRecord/{exerciseRecordId}")
    public ResponseEntity<Void> deleteAllExerciseSetsByExerciseRecord(@PathVariable Long exerciseRecordId) {
        exerciseSetService.deleteAllExerciseSetsByExerciseRecord(exerciseRecordId);
        return ResponseEntity.noContent().build();
    }
}
