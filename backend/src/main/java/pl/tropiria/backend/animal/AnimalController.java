package pl.tropiria.backend.animal;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.CacheControl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static pl.tropiria.backend.config.constants.EndpointConstant.ANIMALS;

@AllArgsConstructor
@RestController
@RequestMapping(ANIMALS)
public class AnimalController {

    private final static String SUCCESSFUL_SAVE_ANIMAL = "animal saved";


    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAnimals() {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(8, TimeUnit.HOURS))
                .body(animalService.getAnimals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveAnimal(@RequestParam("animal") AnimalDto animalDto, @RequestParam("photos") MultipartFile[] photos) {
        animalService.saveAnimal(animalDto, photos);
        return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESSFUL_SAVE_ANIMAL);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }


}
