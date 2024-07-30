package pl.tropiria.backend.animal;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import pl.tropiria.backend.animal.forsale.AnimalForSale;
import pl.tropiria.backend.morph.Morph;
import pl.tropiria.backend.photos.Photos;
import pl.tropiria.backend.species.Species;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String name;

    private String description;

    @Range(min = 0, max = 2)
    private Integer sex;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @ManyToOne
    private Species species;

    @ManyToMany
    private List<Morph> morphs;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Photos> photos;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "animal")
    private AnimalForSale animalForSale;
}
