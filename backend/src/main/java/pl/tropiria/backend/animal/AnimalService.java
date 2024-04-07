package pl.tropiria.backend.animal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.tropiria.backend.animal.forsale.AnimalForSale;
import pl.tropiria.backend.color.Color;
import pl.tropiria.backend.color.ColorService;
import pl.tropiria.backend.config.constants.ReservationConstant;
import pl.tropiria.backend.photos.Photos;
import pl.tropiria.backend.photos.PhotosService;
import pl.tropiria.backend.species.SpeciesService;
import pl.tropiria.backend.utilites.AnimalDtoMapper;
import pl.tropiria.backend.utilites.PhotosDtoMapper;

import java.util.*;

import static pl.tropiria.backend.config.constants.ErrorsConstant.*;
import static pl.tropiria.backend.utilites.AnimalDtoMapper.map;

@Service
@AllArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final PhotosService photosService;
    private final SpeciesService speciesService;
    private final ColorService colorService;

    private static AnimalDto animalDto;

    public List<AnimalDto> getAnimals() {
        return animalRepository
                .findAll()
                .stream()
                .map(AnimalDtoMapper::map)
                .toList();
    }

    public AnimalDto findById(long id) {
        if (isAnimalExists(id)) {
            return map(animalRepository.findById(id));
        }
        throw new IllegalFormatCodePointException(ANIMAL_NOT_FOUND.getCode());
    }

    public void saveAnimal(
            String description,
            Integer sex,
            String dateOfBirth,
            String speciesName,
            List<String> colors,
            MultipartFile[] photos,
            String name,
            Long price,
            String reservationStatus,
            List<String> parents) {
        setAnimalDtoFields(name, description, sex, dateOfBirth, speciesName, colors);
        List<Photos> photosList = photosService.savePhoto(photos);
        animalDto.setPhotos(photosList);
        isAnimalForSale(reservationStatus, price, parents);
        animalRepository.save(AnimalDtoMapper.map(animalDto));
    }

    public void deleteAnimal(long id) {
        if (isAnimalExists(id)) {
            Optional<Animal> animal = Optional.ofNullable(animalRepository.findById(id));
            animal.ifPresent(a -> {
                a.getPhotos()
                        .stream()
                        .map(PhotosDtoMapper::map)
                        .forEach(photosService::deletePhotoFromDisk);
                animalRepository.delete(a);

            });
        }
    }

    private boolean isAnimalExists(long id) {
        return animalRepository.findById(id) != null;
    }

    private boolean isAnimalExists(String name) {
        return animalRepository.findByName(name) != null;
    }

    private void setAnimalDtoFields(String name, String description, Integer sex, String dateOfBirth, String speciesName, List<String> colors) {


        animalDto = new AnimalDto();
        animalDto.setName(name);
        animalDto.setDescription(description);
        animalDto.setDateOfBirth(dateOfBirth);
        animalDto.setSex(sex);
        if (!speciesService.isSpeciesExists(speciesName)) {
            throw new IllegalFormatCodePointException(SPECIES_NOT_FOUND.getCode());
        } else {
            animalDto.setSpecies(speciesService.getSpeciesByName(speciesName));
        }

        List<Color> colorList = new ArrayList<>();
        for (String color : colors) {
            if (!colorService.isColorExists(color)) {
                throw new IllegalFormatCodePointException(COLOR_NOT_FOUND.getCode());
            } else {
                colorList.add(colorService.getColorByName(color));
            }
        }
        animalDto.setColors(colorList);
    }

    private void isAnimalForSale(String reservationStatus, Long price, List<String> parents) {
        if (reservationStatus != null && price != null) {
            if (!validReservationStatus(reservationStatus)) {
                throw new IllegalFormatCodePointException(INVALID_RESERVATION_STATUS.getCode());
            }

            AnimalForSale animalForSale = new AnimalForSale();
            animalForSale.setReservationStatus(reservationStatus);
            animalForSale.setPrice(price);
            List<Animal> parentsList = new ArrayList<>();
            if (parents != null) {
                for (String parent : parents) {
                    if (!isAnimalExists(parent)) {
                        throw new IllegalFormatCodePointException(PARENTS_NOT_FOUND.getCode());
                    }
                    parentsList.add(animalRepository.findByName(parent));
                }
                animalForSale.setParents(parentsList);
            }
            animalDto.setAnimalForSale(animalForSale);
        }
    }


    private boolean validReservationStatus(String reservationStatus) {
        return reservationStatus.equals(ReservationConstant.RESERVED.getStatus()) || reservationStatus.equals(ReservationConstant.FOR_SALE.getStatus());
    }
}
