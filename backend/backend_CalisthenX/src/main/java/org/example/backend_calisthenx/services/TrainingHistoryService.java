package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.models.TrainingHistory;
import org.example.backend_calisthenx.repositories.TrainingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingHistoryService {
    private final TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    public TrainingHistoryService(TrainingHistoryRepository trainingHistoryRepository) {
        this.trainingHistoryRepository = trainingHistoryRepository;
    }

    public Optional<TrainingHistory> getTrainingHistoryById(Long id) {
        return trainingHistoryRepository.findById(id);
    }

    public List<TrainingHistory> getTrainingHistoryByAthlete(Athlete athlete) {
        return trainingHistoryRepository.findByAthlete(athlete);
    }

    public TrainingHistory createTrainingHistory(TrainingHistory trainingHistory) {
        return trainingHistoryRepository.save(trainingHistory);
    }

    public TrainingHistory updateTrainingHistory(TrainingHistory trainingHistory) {
        return trainingHistoryRepository.save(trainingHistory);
    }

    public void deleteTrainingHistoryById(Long id) {
        trainingHistoryRepository.deleteById(id);
    }

}
