package pl.tropiria.backend.animal.forsale;

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
@Embeddable
public class AnimalForSale {

    private Long price;

    @Column(name = "reservation_status")
    private String reservationStatus;

    @ManyToMany
    @Nullable
    private List<Animal> parents;
}
