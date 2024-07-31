package pl.tropiria.backend.animal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.tropiria.backend.config.constants.SexConstant;
import pl.tropiria.backend.morph.Morph;
import pl.tropiria.backend.morph.MorphService;
import pl.tropiria.backend.config.constants.ReservationConstant;
import pl.tropiria.backend.photos.Photos;
import pl.tropiria.backend.photos.PhotosDto;
import pl.tropiria.backend.photos.PhotosService;
import pl.tropiria.backend.species.SpeciesService;

import java.util.*;

import static pl.tropiria.backend.config.constants.ErrorsConstant.*;


@Service
@AllArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final PhotosService photosService;
    private final SpeciesService speciesService;
    private final MorphService morphService;

    public List<AnimalDto> getAnimals() {
        return animalRepository
                .findAll()
                .stream()
                .map(animal -> AnimalDto.builder()
                        .id(animal.getId())
                        .name(animal.getName())
                        .description(animal.getDescription())
                        .sex(animal.getSex())
                        .dateOfBirth(animal.getDateOfBirth())
                        .species(animal.getSpecies())
                        .morphs(animal.getMorphs())
                        .photos(animal.getPhotos())
                        .animalForSale(animal.getAnimalForSale())
                        .build())
                .toList();
    }

    public AnimalDto findById(long id) {
        if (isAnimalExists(id)) {
            Animal animal = animalRepository.findById(id);
            return AnimalDto.builder().
                    id(animal.getId())
                    .name(animal.getName())
                    .description(animal.getDescription())
                    .sex(animal.getSex())
                    .dateOfBirth(animal.getDateOfBirth())
                    .species(animal.getSpecies())
                    .morphs(animal.getMorphs())
                    .photos(animal.getPhotos())
                    .animalForSale(animal.getAnimalForSale())
                    .build();
        }
        throw new IllegalFormatCodePointException(ANIMAL_NOT_FOUND.CODE);
    }

    public void saveAnimal(String animalJson, MultipartFile[] photos) {
        List<Photos> photosList = photosService.savePhoto(photos);
        AnimalDto animalDto = mapJsonToDto(animalJson);
        animalDto.setPhotos(photosList);
        checkAnimalDto(animalDto);
        animalRepository.save(Animal.builder()
                .name(animalDto.getName())
                .description(animalDto.getDescription())
                .sex(animalDto.getSex())
                .dateOfBirth(animalDto.getDateOfBirth())
                .species(animalDto.getSpecies())
                .morphs(animalDto.getMorphs())
                .photos(animalDto.getPhotos())
                .animalForSale(animalDto.getAnimalForSale())
                .build());

    }

    private AnimalDto mapJsonToDto(String animalJson) {
        try {
            return new ObjectMapper().readValue(animalJson, AnimalDto.class);
        }
        catch (JsonProcessingException e) {
            throw new IllegalFormatCodePointException(INVALID_JSON.CODE);
        }
    }

    private void checkAnimalDto(AnimalDto animalDto) {
        if (animalDto.getAnimalForSale() != null) {
            if (!validReservationStatus(animalDto.getAnimalForSale().getReservationStatus())) {
                throw new IllegalFormatCodePointException(INVALID_RESERVATION_STATUS.CODE);
            }
        }
        if (!validSex(animalDto.getSex())) {
            throw new IllegalFormatCodePointException(INVALID_SEX.CODE);
        }
        if (!speciesService.isSpeciesExists(animalDto.getSpecies().getName())) {
            throw new IllegalFormatCodePointException(SPECIES_NOT_FOUND.CODE);
        }
        else
        {
            animalDto.setSpecies(speciesService.getSpeciesByName(animalDto.getSpecies().getName()));
        }


        List<Morph> morphList = new ArrayList<>();

        for (Morph morph : animalDto.getMorphs()) {
            if (!morphService.isMorphExists(morph.getName())) {
                throw new IllegalFormatCodePointException(MORPH_NOT_FOUND.CODE);
            }
            else
            {
                morphList.add(morphService.getMorphByName(morph.getName()));
            }

        }
        animalDto.setMorphs(morphList);
    }

    public void deleteAnimal(long id) {
        if (isAnimalExists(id)) {
            Optional<Animal> animal = Optional.ofNullable(animalRepository.findById(id));
            animal.ifPresent(a -> {
                a.getPhotos()
                        .stream()
                        .map(photos -> PhotosDto.builder()
                                .id(photos.getId())
                                .photoName(photos.getPhotoName())
                                .build())
                        .forEach(photosDto -> {
                            photosService.deletePhotoFromDisk(photosDto);
                            photosService.deletePhotoNameFromDatabase(photosDto);
                        });
                animalRepository.delete(a);

            });
        }
    }

    public Page<Animal> getAnimalsForSale(Pageable pagable)
    {
        return animalRepository.findAllAnimalsForSale(pagable);
    }

    private boolean isAnimalExists(long id) {
        return animalRepository.findById(id) != null;
    }


    private boolean validReservationStatus(String reservationStatus) {
        return reservationStatus.equals(ReservationConstant.RESERVED) || reservationStatus.equals(ReservationConstant.FOR_SALE);
    }

    private boolean validSex(int sex)
    {
        return sex == SexConstant.UNKNOWN || sex == SexConstant.MALE || sex == SexConstant.FEMALE;
    }
}
