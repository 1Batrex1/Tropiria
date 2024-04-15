package pl.tropiria.backend.animal;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tropiria.backend.animal.forsale.AnimalForSale;
import pl.tropiria.backend.morph.Morph;
import pl.tropiria.backend.photos.Photos;
import pl.tropiria.backend.species.Species;

import java.util.List;

@Data
@NoArgsConstructor
public class AnimalDto {


    private Long id;

    private String name;

    private String description;

    private Integer sex;

    private String dateOfBirth;

    private Species species;

    private List<Morph> morphs;

    private List<Photos> photos;

    private AnimalForSale animalForSale;

    public AnimalDto(String description,
                     Integer sex,
                     String dateOfBirth,
                     Species species,
                     List<Morph> morphs) {
        this.description = description;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.species = species;
        this.morphs = morphs;

    }

}
