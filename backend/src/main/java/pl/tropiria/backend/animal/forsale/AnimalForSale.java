package pl.tropiria.backend.animal.forsale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tropiria.backend.animal.Animal;
import pl.tropiria.backend.animal.AnimalRepository;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "animal_for_sale")
public class AnimalForSale {

    @Id
    @OneToOne
    @JoinColumn(name = "animal_id")
    @JsonIgnore
    private Animal animal;


    private Long price;

    @Column(name = "reservation_status")
    private String reservationStatus;

    @ManyToMany
    @Nullable
    private List<Animal> parents;
}
