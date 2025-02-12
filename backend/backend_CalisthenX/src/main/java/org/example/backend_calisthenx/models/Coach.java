package org.example.backend_calisthenx.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Athlete> athletes;

    private String CoachBrand;
}
