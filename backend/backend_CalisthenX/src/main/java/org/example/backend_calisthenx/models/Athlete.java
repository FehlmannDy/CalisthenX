package org.example.backend_calisthenx.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "athletes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Athlete extends User {

    @ManyToOne
    @JoinColumn(name = "coach_id") // Clé étrangère pour relier un athlète à son coach
    private Coach coach;

}

