package org.example.backend_calisthenx.controllers;

import org.example.backend_calisthenx.models.ExerciseRecord;
import org.example.backend_calisthenx.services.ExerciseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-records")
public class ExerciseRecordController {

    private final ExerciseRecordService exerciseRecordService;

    @Autowired
    public ExerciseRecordController(ExerciseRecordService exerciseRecordService) {
        this.exerciseRecordService = exerciseRecordService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseRecord> getExerciseRecordById(@PathVariable Long id) {
        ExerciseRecord exerciseRecord = exerciseRecordService.getExerciseRecordById(id);
        return ResponseEntity.ok(exerciseRecord);
    }

    @GetMapping("/training/{trainingHistoryId}")
    public ResponseEntity<List<ExerciseRecord>> getExerciseRecordsByTrainingHistory(@PathVariable Long trainingHistoryId) {
        List<ExerciseRecord> records = exerciseRecordService.getExerciseRecordsByTrainingHistory(trainingHistoryId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<List<ExerciseRecord>> getExerciseRecordsByExercise(@PathVariable Long exerciseId) {
        List<ExerciseRecord> records = exerciseRecordService.getExerciseRecordsByExercise(exerciseId);
        return ResponseEntity.ok(records);
    }

    @PostMapping
    public ResponseEntity<ExerciseRecord> createExerciseRecord(@RequestBody ExerciseRecord exerciseRecord) {
        ExerciseRecord createdRecord = exerciseRecordService.createExerciseRecord(exerciseRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseRecord> updateExerciseRecord(@PathVariable Long id, @RequestBody ExerciseRecord updatedRecord) {
        ExerciseRecord updated = exerciseRecordService.updateExerciseRecord(id, updatedRecord);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseRecord(@PathVariable Long id) {
        exerciseRecordService.deleteExerciseRecordById(id);
        return ResponseEntity.noContent().build();
    }
}

