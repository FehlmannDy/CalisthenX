package org.example.backend_calisthenx.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercise_sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Numéro de la série (exemple : 1 pour la première série, 2 pour la deuxième, etc.)
    private Integer setNumber;

    // Nombre de répétitions ou durée (en secondes) pour cette série
    private Integer repsOrDuration;

    private Integer actualRepetitions;

    @ManyToOne
    @JoinColumn(name = "exercise_record_id", nullable = false)
    private ExerciseRecord exerciseRecord;
}
