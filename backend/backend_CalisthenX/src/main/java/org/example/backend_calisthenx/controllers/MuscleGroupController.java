package org.example.backend_calisthenx.controllers;

import org.example.backend_calisthenx.exceptions.ResourceNotFoundException;
import org.example.backend_calisthenx.models.MuscleGroup;
import org.example.backend_calisthenx.services.MuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/muscleGroups")
public class MuscleGroupController {

    @Autowired
    private MuscleGroupService muscleGroupService;

    @GetMapping
    public ResponseEntity<List<MuscleGroup>> getAllMuscleGroups() {
        List<MuscleGroup> muscleGroups = muscleGroupService.getAllMuscleGroups();
        if (muscleGroups.isEmpty()) {
            throw new ResourceNotFoundException("Muscle groups not found");
        }
        return ResponseEntity.ok(muscleGroups);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MuscleGroup> getMuscleGroupByName(@PathVariable String name) {
        Optional<MuscleGroup> muscleGroup = muscleGroupService.getMuscleGroupByName(name);
        return muscleGroup.map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Muscle group name " + name + " not found"));
    }

    @PostMapping
    public ResponseEntity<MuscleGroup> createMuscleGroup(@RequestBody MuscleGroup muscleGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(muscleGroupService.saveMuscleGroup(muscleGroup));
    }

    @PutMapping
    public ResponseEntity<MuscleGroup> updateMuscleGroup(@RequestBody MuscleGroup muscleGroup) {
        if(muscleGroup.getId() == null) {
            throw new ResourceNotFoundException("Muscle group id not found");
        }
        return ResponseEntity.ok(muscleGroupService.updateMuscleGroup(muscleGroup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMuscleGroup(@PathVariable Long id) {
        muscleGroupService.deleteMuscleGroup(id);
        return ResponseEntity.noContent().build();
    }
}

