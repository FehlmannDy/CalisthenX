package org.example.backend_calisthenx.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "coaches")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coach extends User{
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Athlete> athletes;

    private String CoachBrand;
}
