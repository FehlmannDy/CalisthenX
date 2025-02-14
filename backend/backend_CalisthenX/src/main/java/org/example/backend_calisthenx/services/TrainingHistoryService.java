package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Athlete;
import org.example.backend_calisthenx.models.TrainingHistory;
import org.example.backend_calisthenx.repositories.AthleteRepository;
import org.example.backend_calisthenx.repositories.TrainingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingHistoryService {
    private final TrainingHistoryRepository trainingHistoryRepository;
    private final AthleteRepository athleteRepository;

    @Autowired
    public TrainingHistoryService(TrainingHistoryRepository trainingHistoryRepository, AthleteRepository athleteRepository) {
        this.trainingHistoryRepository = trainingHistoryRepository;
        this.athleteRepository = athleteRepository;
    }

    public Optional<TrainingHistory> getTrainingHistoryById(Long id) {
        return trainingHistoryRepository.findById(id);
    }

    public List<TrainingHistory> getTrainingHistoryByAthlete(Athlete athlete) {
        if(!athleteRepository.existsById(athlete.getId())){
            throw new ResourceNotFoundException("Athlete not found");
        }
        return trainingHistoryRepository.findByAthlete(athlete);
    }

    public TrainingHistory createTrainingHistory(TrainingHistory trainingHistory) {
        if(!athleteRepository.existsById(trainingHistory.getAthlete().getId())) {
            throw new ResourceNotFoundException("Athlete not found");
        }
        return trainingHistoryRepository.save(trainingHistory);
    }

    public TrainingHistory updateTrainingHistory(TrainingHistory trainingHistory) {
        if (!trainingHistoryRepository.existsById(trainingHistory.getId())) {
            throw new ResourceNotFoundException("Training history not found with ID: " + trainingHistory.getId());
        }
        if(!athleteRepository.existsById(trainingHistory.getAthlete().getId())) {
            throw new ResourceNotFoundException("Athlete not found");
        }
        return trainingHistoryRepository.save(trainingHistory);
    }

    public void deleteTrainingHistoryById(Long id) {
        trainingHistoryRepository.deleteById(id);
    }

}
