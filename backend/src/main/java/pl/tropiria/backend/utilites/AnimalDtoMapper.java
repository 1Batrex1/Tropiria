package pl.tropiria.backend.utilites;

import pl.tropiria.backend.animal.Animal;
import pl.tropiria.backend.animal.AnimalDto;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.UTILITY_CLASS;

public class AnimalDtoMapper {

    private AnimalDtoMapper() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.getCode());
    }

    public static AnimalDto map(Animal animal) {
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName(animal.getName());
        animalDto.setId(animal.getId());
        animalDto.setDescription(animal.getDescription());
        animalDto.setSex(animal.getSex());
        animalDto.setDateOfBirth(animal.getDateOfBirth());
        animalDto.setSpecies(animal.getSpecies());
        animalDto.setMorphs(animal.getMorphs());
        animalDto.setPhotos(animal.getPhotos());
        animalDto.setAnimalForSale(animal.getAnimalForSale());
        return animalDto;
    }

    public static Animal map(AnimalDto animalDto) {
        Animal animal = new Animal();
        animal.setId(animalDto.getId());
        animal.setName(animalDto.getName());
        animal.setDescription(animalDto.getDescription());
        animal.setSex(animalDto.getSex());
        animal.setDateOfBirth(animalDto.getDateOfBirth());
        animal.setSpecies(animalDto.getSpecies());
        animal.setMorphs(animalDto.getMorphs());
        animal.setPhotos(animalDto.getPhotos());
        animal.setAnimalForSale(animalDto.getAnimalForSale());
        return animal;
    }


}
