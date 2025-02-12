package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.Exercise;
import org.example.backend_calisthenx.models.ExerciseRecord;
import org.example.backend_calisthenx.models.TrainingHistory;
import org.example.backend_calisthenx.repositories.ExerciseRecordRepository;
import org.example.backend_calisthenx.repositories.ExerciseRepository;
import org.example.backend_calisthenx.repositories.TrainingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseRecordService {

    private final ExerciseRecordRepository exerciseRecordRepository;
    private final TrainingHistoryRepository trainingHistoryRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseRecordService(ExerciseRecordRepository exerciseRecordRepository,
                                 TrainingHistoryRepository trainingHistoryRepository,
                                 ExerciseRepository exerciseRepository) {
        this.exerciseRecordRepository = exerciseRecordRepository;
        this.trainingHistoryRepository = trainingHistoryRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<ExerciseRecord> getExerciseRecordsByTrainingHistory(Long trainingHistoryId) {
        TrainingHistory trainingHistory = trainingHistoryRepository.findById(trainingHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("TrainingHistory not found with ID: " + trainingHistoryId));
        return exerciseRecordRepository.findByTrainingHistory(trainingHistory);
    }

    public List<ExerciseRecord> getExerciseRecordsByExercise(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with ID: " + exerciseId));
        return exerciseRecordRepository.findByExercise(exercise);
    }

    public ExerciseRecord getExerciseRecordById(Long id) {
        return exerciseRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExerciseRecord not found with ID: " + id));
    }

    public ExerciseRecord createExerciseRecord(ExerciseRecord exerciseRecord) {
        validateExerciseRecord(exerciseRecord);
        return exerciseRecordRepository.save(exerciseRecord);
    }

    public ExerciseRecord updateExerciseRecord(Long id, ExerciseRecord updatedRecord) {
        ExerciseRecord existingRecord = getExerciseRecordById(id);
        validateExerciseRecord(updatedRecord);

        existingRecord.setTrainingHistory(updatedRecord.getTrainingHistory());
        existingRecord.setExercise(updatedRecord.getExercise());
        existingRecord.setMethod(updatedRecord.getMethod());
        existingRecord.setWeight(updatedRecord.getWeight());
        existingRecord.setCoachComment(updatedRecord.getCoachComment());
        existingRecord.setExerciseSets(updatedRecord.getExerciseSets());

        return exerciseRecordRepository.save(existingRecord);
    }

    public void deleteExerciseRecordById(Long id) {
        if (!exerciseRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("ExerciseRecord not found with ID: " + id);
        }
        exerciseRecordRepository.deleteById(id);
    }

    private void validateExerciseRecord(ExerciseRecord exerciseRecord) {
        if (exerciseRecord.getTrainingHistory() == null ||
                !trainingHistoryRepository.existsById(exerciseRecord.getTrainingHistory().getId())) {
            throw new ResourceNotFoundException("Invalid TrainingHistory ID: " +
                    (exerciseRecord.getTrainingHistory() != null ? exerciseRecord.getTrainingHistory().getId() : "null"));
        }

        if (exerciseRecord.getExercise() == null ||
                !exerciseRepository.existsById(exerciseRecord.getExercise().getId())) {
            throw new ResourceNotFoundException("Invalid Exercise ID: " +
                    (exerciseRecord.getExercise() != null ? exerciseRecord.getExercise().getId() : "null"));
        }
    }
}

