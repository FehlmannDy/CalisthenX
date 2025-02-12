package org.example.backend_calisthenx.controllers;

import lombok.RequiredArgsConstructor;
import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.TrainingHistory;
import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.services.AthleteService;
import org.example.backend_calisthenx.services.TrainingHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-history")
@RequiredArgsConstructor
public class TrainingHistoryController {

    private final TrainingHistoryService trainingHistoryService;
    private final AthleteService athleteService;


    @GetMapping("/{id}")
    public ResponseEntity<TrainingHistory> getTrainingHistoryById(@PathVariable Long id) {
        return trainingHistoryService.getTrainingHistoryById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Training history not found with ID: " + id));
    }

    @GetMapping("/athlete/{athleteId}")
    public ResponseEntity<List<TrainingHistory>> getTrainingHistoriesByAthlete(@PathVariable Long athleteId) {
        Athlete athlete = athleteService.getAthleteById(athleteId)
                .orElseThrow(() -> new ResourceNotFoundException("Athlete not found with ID: " + athleteId));

        List<TrainingHistory> histories = trainingHistoryService.getTrainingHistoryByAthlete(athlete);
        return ResponseEntity.ok(histories);
    }

    @PostMapping
    public ResponseEntity<TrainingHistory> createTrainingHistory(@RequestBody TrainingHistory trainingHistory) {
        // Vérifier si l'athlète existe
        if (trainingHistory.getAthlete() == null ||
                athleteService.getAthleteById(trainingHistory.getAthlete().getId()).isEmpty()) {
            assert trainingHistory.getAthlete() != null;
            throw new ResourceNotFoundException("Athlete not found with ID: " + trainingHistory.getAthlete().getId());
        }

        TrainingHistory createdTrainingHistory = trainingHistoryService.createTrainingHistory(trainingHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainingHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingHistory> updateTrainingHistory(@PathVariable Long id, @RequestBody TrainingHistory trainingHistory) {
        if (trainingHistoryService.getTrainingHistoryById(id).isEmpty()) {
            throw new ResourceNotFoundException("Training history not found with ID: " + id);
        }

        // Vérifier si l'athlète existe avant la mise à jour
        if (trainingHistory.getAthlete() == null ||
                athleteService.getAthleteById(trainingHistory.getAthlete().getId()).isEmpty()) {
            assert trainingHistory.getAthlete() != null;
            throw new ResourceNotFoundException("Athlete not found with ID: " + trainingHistory.getAthlete().getId());
        }

        trainingHistory.setId(id);
        TrainingHistory updatedTrainingHistory = trainingHistoryService.updateTrainingHistory(trainingHistory);
        return ResponseEntity.ok(updatedTrainingHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingHistoryById(@PathVariable Long id) {
        if (trainingHistoryService.getTrainingHistoryById(id).isEmpty()) {
            throw new ResourceNotFoundException("Training history not found with ID: " + id);
        }
        trainingHistoryService.deleteTrainingHistoryById(id);
        return ResponseEntity.noContent().build();
    }
}

