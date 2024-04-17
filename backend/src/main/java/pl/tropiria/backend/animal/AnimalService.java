package pl.tropiria.backend.animal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
        throw new IllegalFormatCodePointException(ANIMAL_NOT_FOUND.getCode());
    }

    public void saveAnimal(AnimalDto animalDto, MultipartFile[] photos) {
        List<Photos> photosList = photosService.savePhoto(photos);
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

    private void checkAnimalDto(AnimalDto animalDto) {
        if (animalDto.getAnimalForSale() != null) {
            if (!validReservationStatus(animalDto.getAnimalForSale().getReservationStatus())) {
                throw new IllegalFormatCodePointException(INVALID_RESERVATION_STATUS.getCode());
            }
        }
        if (!speciesService.isSpeciesExists(animalDto.getSpecies().getName())) {
            throw new IllegalFormatCodePointException(SPECIES_NOT_FOUND.getCode());
        }
        for (Morph morph : animalDto.getMorphs()) {
            if (!morphService.isMorphExists(morph.getName())) {
                throw new IllegalFormatCodePointException(MORPH_NOT_FOUND.getCode());
            }
        }
    }

    public void deleteAnimal(long id) {
        if (isAnimalExists(id)) {
            Optional<Animal> animal = Optional.ofNullable(animalRepository.findById(id));
            animal.ifPresent(a -> {
                a.getPhotos()
                        .stream()
                        .map(photos -> PhotosDto.builder()
                                .id(photos.getId())
                                .photoPath(photos.getPhotoPath())
                                .build())
                        .forEach(photosDto -> {
                            photosService.deletePhotoFromDisk(photosDto);
                            photosService.deletePhotoPathFromDatabase(photosDto);
                        });
                animalRepository.delete(a);

            });
        }
    }

    private boolean isAnimalExists(long id) {
        return animalRepository.findById(id) != null;
    }


    private boolean validReservationStatus(String reservationStatus) {
        return reservationStatus.equals(ReservationConstant.RESERVED.getStatus()) || reservationStatus.equals(ReservationConstant.FOR_SALE.getStatus());
    }
}
