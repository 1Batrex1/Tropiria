package pl.tropiria.backend.animal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import pl.tropiria.backend.animal.forsale.AnimalForSale;
import pl.tropiria.backend.color.Color;
import pl.tropiria.backend.photos.Photos;
import pl.tropiria.backend.species.Species;

import java.util.Date;
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

    private List<Color> colors;

    private List<Photos> photos;

    private AnimalForSale animalForSale;

    public AnimalDto(String description,
                     Integer sex,
                     String dateOfBirth,
                     Species species,
                     List<Color> colors) {
        this.description = description;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.species = species;
        this.colors = colors;

    }

}
