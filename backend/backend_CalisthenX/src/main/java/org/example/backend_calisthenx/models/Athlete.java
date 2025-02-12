package org.example.backend_calisthenx.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "athletes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Athlete extends User {

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    @JsonBackReference
    private Coach coach;

}

