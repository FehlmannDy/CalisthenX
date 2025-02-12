package org.example.backend_calisthenx.controllers;

import org.example.backend_calisthenx.models.MuscleGroup;
import org.example.backend_calisthenx.services.MuscleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MuscleGroup> getAllMuscleGroups() {
        return muscleGroupService.getAllMuscleGroups();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MuscleGroup> getMuscleGroupByName(@PathVariable String name) {
        Optional<MuscleGroup> muscleGroup = muscleGroupService.getMuscleGroupByName(name);
        return muscleGroup.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MuscleGroup createMuscleGroup(@RequestBody MuscleGroup muscleGroup) {
        return muscleGroupService.saveMuscleGroup(muscleGroup);
    }

    @PutMapping
    public MuscleGroup updateMuscleGroup(@RequestBody MuscleGroup muscleGroup) {
        return muscleGroupService.updateMuscleGroup(muscleGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMuscleGroup(@PathVariable Long id) {
        muscleGroupService.deleteMuscleGroup(id);
        return ResponseEntity.noContent().build();
    }
}

