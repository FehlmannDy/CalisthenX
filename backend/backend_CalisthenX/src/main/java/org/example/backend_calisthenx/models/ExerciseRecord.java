package org.example.backend_calisthenx.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lien vers la séance d'entraînement
    @ManyToOne
    @JoinColumn(name = "training_history_id", nullable = false)
    private TrainingHistory trainingHistory;

    // Lien vers l'exercice générique (par exemple, "Traction")
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    // Méthode d'exécution globale (RIR, ENOM, AMRAP, etc.) pour cet exercice
    @Enumerated(EnumType.STRING)
    private TrainingMethod method;

    private double weight;

    private String coachComment;

    // Liste des séries réalisées pour cet exercice
    @OneToMany(mappedBy = "exerciseRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> exerciseSets = new ArrayList<>();
}

