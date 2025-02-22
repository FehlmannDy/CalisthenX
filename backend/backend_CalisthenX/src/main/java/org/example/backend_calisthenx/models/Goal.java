package org.example.backend_calisthenx.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goals") // Cette annotation permet de spécifier la table goals
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer targetValue; // Par exemple, 50 pompes
    private boolean validated;
    private LocalDateTime createdAt;

    // Relation avec l'athlète auquel l'objectif est assigné
    @ManyToOne
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

    // Relation auto-référentielle pour les sous-objectifs
    @ManyToOne
    @JoinColumn(name = "parent_goal_id")
    private Goal parentGoal;

    @OneToMany(mappedBy = "parentGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> subGoals = new ArrayList<>();

}
