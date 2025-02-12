package org.example.backend_calisthenx.services;

import org.example.backend_calisthenx.models.MuscleGroup;
import org.example.backend_calisthenx.repositories.MuscleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuscleGroupService {

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    public List<MuscleGroup> getAllMuscleGroups() {
        return muscleGroupRepository.findAll();
    }

    public Optional<MuscleGroup> getMuscleGroupByName(String name) {
        return muscleGroupRepository.findByName(name);
    }

    public MuscleGroup saveMuscleGroup(MuscleGroup muscleGroup) {
        return muscleGroupRepository.save(muscleGroup);
    }

    public MuscleGroup updateMuscleGroup(MuscleGroup muscleGroup) {
        return muscleGroupRepository.save(muscleGroup);
    }

    public void deleteMuscleGroup(Long id) {
        muscleGroupRepository.deleteById(id);
    }
}
