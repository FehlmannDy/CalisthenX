package org.example.backend_calisthenx.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDateTime date;

    private boolean finished;
    private LocalDateTime finishTime;
    private String coachComment;
    private String coachVideoUrl;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    // Liste des exercices réalisés pendant la séance
    @OneToMany(mappedBy = "trainingHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseRecord> exerciseRecords = new ArrayList<>();
}

