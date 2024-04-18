package pl.tropiria.backend.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tropiria.backend.animal.forsale.AnimalForSale;
import pl.tropiria.backend.morph.Morph;
import pl.tropiria.backend.photos.Photos;
import pl.tropiria.backend.species.Species;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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


}
