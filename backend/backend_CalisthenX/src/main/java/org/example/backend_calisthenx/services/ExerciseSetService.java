package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.models.ExerciseSet;
import org.example.backend_calisthenx.repositories.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseSetService {

    @Autowired
    private ExerciseSetRepository exerciseSetRepository;

    public List<ExerciseSet> getExerciseSetsByExerciseRecord(Long exerciseRecordId) {
        return exerciseSetRepository.findByExerciseRecordId(exerciseRecordId);
    }

    public Optional<ExerciseSet> getExerciseSetByIdAndExerciseRecord(Long id, Long exerciseRecordId) {
        return Optional.ofNullable(exerciseSetRepository.findByIdAndExerciseRecordId(id, exerciseRecordId));
    }

    public ExerciseSet createExerciseSet(ExerciseSet exerciseSet) {
        return exerciseSetRepository.save(exerciseSet);
    }

    public ExerciseSet updateExerciseSet(ExerciseSet exerciseSet) {
        return exerciseSetRepository.save(exerciseSet); // La méthode save permet également de mettre à jour.
    }

    public void deleteExerciseSet(Long id, Long exerciseRecordId) {
        Optional<ExerciseSet> exerciseSet = getExerciseSetByIdAndExerciseRecord(id, exerciseRecordId);
        exerciseSet.ifPresent(exerciseSetRepository::delete);
    }

    public void deleteAllExerciseSetsByExerciseRecord(Long exerciseRecordId) {
        List<ExerciseSet> exerciseSets = getExerciseSetsByExerciseRecord(exerciseRecordId);
        exerciseSetRepository.deleteAll(exerciseSets);
    }
}

