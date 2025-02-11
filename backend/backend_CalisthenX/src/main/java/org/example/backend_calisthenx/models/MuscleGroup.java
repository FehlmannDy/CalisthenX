package org.example.backend_calisthenx.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "muscle_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Relation inverse Many-to-Many
    @OneToMany(mappedBy = "muscleGroup", cascade = CascadeType.ALL)
    private List<ExerciseMuscleGroup> exercises = new ArrayList<>();
}

