package pl.tropiria.backend.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tropiria.backend.animal.forsale.AnimalForSale;
import pl.tropiria.backend.morph.Morph;
import pl.tropiria.backend.photo.Photo;
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

    private List<Photo> photoList;

    private AnimalForSale animalForSale;


    public static AnimalDto toDto(Animal animal) {
        return AnimalDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .description(animal.getDescription())
                .sex(animal.getSex())
                .dateOfBirth(animal.getDateOfBirth())
                .species(animal.getSpecies())
                .morphs(animal.getMorphs())
                .photoList(animal.getPhotoList())
                .animalForSale(animal.getAnimalForSale())
                .build();
    }

    public static Animal toEntity(AnimalDto animalDto) {
        return Animal.builder()
                .id(animalDto.getId())
                .name(animalDto.getName())
                .description(animalDto.getDescription())
                .sex(animalDto.getSex())
                .dateOfBirth(animalDto.getDateOfBirth())
                .species(animalDto.getSpecies())
                .morphs(animalDto.getMorphs())
                .photoList(animalDto.getPhotoList())
                .animalForSale(animalDto.getAnimalForSale())
                .build();


    }
}

