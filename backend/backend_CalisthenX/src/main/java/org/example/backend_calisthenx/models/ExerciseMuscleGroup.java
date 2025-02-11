package org.example.backend_calisthenx.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercise_muscle_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseMuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation avec Exercise
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    // Relation avec MuscleGroup
    @ManyToOne
    @JoinColumn(name = "muscle_group_id", nullable = false)
    private MuscleGroup muscleGroup;

    // Ordre de sollicitation (1 = muscle principal, 2 = muscle secondaire, etc.)
    private int solicitationOrder;
}
