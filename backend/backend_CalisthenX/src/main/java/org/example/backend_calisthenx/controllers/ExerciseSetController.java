package org.example.backend_calisthenx.controllers;

import org.example.backend_calisthenx.models.ExerciseSet;
import org.example.backend_calisthenx.services.ExerciseSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exerciseSets")
public class ExerciseSetController {

    @Autowired
    private ExerciseSetService exerciseSetService;

    @GetMapping("/exerciseRecord/{exerciseRecordId}")
    public List<ExerciseSet> getExerciseSetsByExerciseRecord(@PathVariable Long exerciseRecordId) {
        return exerciseSetService.getExerciseSetsByExerciseRecord(exerciseRecordId);
    }

    @PostMapping
    public ExerciseSet createExerciseSet(@RequestBody ExerciseSet exerciseSet) {
        return exerciseSetService.createExerciseSet(exerciseSet);
    }

    @PutMapping
    public ExerciseSet updateExerciseSet(@RequestBody ExerciseSet exerciseSet) {
        return exerciseSetService.updateExerciseSet(exerciseSet);
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
