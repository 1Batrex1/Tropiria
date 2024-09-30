package pl.tropiria.backend.animal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.tropiria.backend.config.constants.SexConstant;
import pl.tropiria.backend.morph.Morph;
import pl.tropiria.backend.morph.MorphService;
import pl.tropiria.backend.config.constants.ReservationConstant;
import pl.tropiria.backend.photo.Photo;
import pl.tropiria.backend.photo.PhotoDto;
import pl.tropiria.backend.photo.PhotoService;
import pl.tropiria.backend.species.SpeciesService;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static pl.tropiria.backend.config.constants.ErrorsConstant.*;


@Service
@AllArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final PhotoService photoService;
    private final SpeciesService speciesService;
    private final MorphService morphService;

    public Page<AnimalDto> getAnimals(Pageable pageable) {
        return animalRepository
                .findAll(pageable)
                .map(AnimalDto::toDto);
    }

    public AnimalDto findById(long id) {
        if (isAnimalExists(id)) {
            Animal animal = animalRepository.findById(id);
            return AnimalDto.toDto(animal);
        }
        throw new IllegalFormatCodePointException(ANIMAL_NOT_FOUND.CODE);
    }

    @Transactional
    public AnimalDto saveAnimal(String animalJson, MultipartFile[] photoMultipartList) {

        photoService.checkPhotosLimit(photoMultipartList);

        List<PhotoDto> photoDtoList = photoService.saveMultiplePhotoInDataBase(photoMultipartList);
        List<Photo> photoList = photoDtoList.stream()
                .map(PhotoDto::toEntity)
                .collect(toList());
        AnimalDto animalDto = mapJsonToDto(animalJson);
        animalDto.setPhotoList(photoList);

        checkAnimalDto(animalDto);
        Animal animal = animalRepository.save(AnimalDto.toEntity(animalDto));

        return AnimalDto.toDto(animal);

    }

    private AnimalDto mapJsonToDto(String animalJson) {
        try {
            return new ObjectMapper().readValue(animalJson, AnimalDto.class);
        } catch (JsonProcessingException e) {
            throw new IllegalFormatCodePointException(INVALID_JSON.CODE);
        }
    }

    private void checkAnimalDto(AnimalDto animalDto) {
        if (animalDto.getAnimalForSale() != null) {
            if (validReservationStatus(animalDto.getAnimalForSale().getReservationStatus())) {
                throw new IllegalFormatCodePointException(INVALID_RESERVATION_STATUS.CODE);
            }
        }
        if (!validSex(animalDto.getSex())) {
            throw new IllegalFormatCodePointException(INVALID_SEX.CODE);
        }
        if (!speciesService.isSpeciesExists(animalDto.getSpecies().getName())) {
            throw new IllegalFormatCodePointException(SPECIES_NOT_FOUND.CODE);
        } else {
            animalDto.setSpecies(speciesService.getSpeciesByName(animalDto.getSpecies().getName()));
        }


        List<Morph> morphList = new ArrayList<>();

        for (Morph morph : animalDto.getMorphs()) {
            if (!morphService.isMorphExists(morph.getName())) {
                throw new IllegalFormatCodePointException(MORPH_NOT_FOUND.CODE);
            } else {
                morphList.add(morphService.getMorphByName(morph.getName()));
            }

        }
        animalDto.setMorphs(morphList);
    }

    public void deleteAnimal(long id) {
        if (isAnimalExists(id)) {
            Optional<Animal> animal = Optional.ofNullable(animalRepository.findById(id));
            animal.ifPresent(a -> {

                if (a.getAnimalForSale() == null) {
                    List<Animal> childAnimals = animalRepository.findAllByParent(a);
                    childAnimals.forEach(child -> {
                        if (child.getAnimalForSale().getParents() != null) {
                            child.getAnimalForSale().getParents().remove(a);
                        }
                        animalRepository.save(child);
                    });

                }
                List<Photo> photo = a.getPhotoList();
                photo.forEach(p -> photoService.deletePhoto(PhotoDto.toDto(p)));
                animalRepository.delete(a);


            });
        }
    }

    public AnimalDto updateReservationStatus(long id, String status) {
        if (isAnimalExists(id) && validReservationStatus(status)) {
            Animal animal = animalRepository.findById(id);
            if (animal.getAnimalForSale() != null) {
                animal.getAnimalForSale().setReservationStatus(status);
                animal = animalRepository.save(animal);
                return AnimalDto.toDto(animal);
            } else {
                throw new IllegalFormatCodePointException(ANIMAL_NOT_FOR_SALE.CODE);
            }
        } else {
            throw new IllegalFormatCodePointException(ANIMAL_NOT_FOUND.CODE);
        }
    }

    public Page<AnimalDto> getAnimalsForSale(Pageable pagable) {
        return animalRepository.findAllAnimalsForSale(pagable)
                .map(AnimalDto::toDto);

    }

    public List<AnimalDto> getParents() {
        return animalRepository.findAllParents().stream().map(AnimalDto::toDto).toList();
    }

    private boolean isAnimalExists(long id) {
        return animalRepository.findById(id) != null;
    }


    private boolean validReservationStatus(String reservationStatus) {
        return !reservationStatus.equals(ReservationConstant.RESERVED)
                && !reservationStatus.equals(ReservationConstant.FOR_SALE)
                && !reservationStatus.equals(ReservationConstant.SOLD);
    }

    private boolean validSex(int sex) {
        return sex == SexConstant.UNKNOWN || sex == SexConstant.MALE || sex == SexConstant.FEMALE;
    }


}
