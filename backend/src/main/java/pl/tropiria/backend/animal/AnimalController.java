package pl.tropiria.backend.animal;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private static final String SUCCESSFUL_SAVE_ANIMAL = "animal saved";



    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAnimals() {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(8, TimeUnit.HOURS))
                .body(animalService.getAnimals());
    }

    @GetMapping("/for-sale")
    public ResponseEntity<Page<Animal>> getAnimalsForSale(Pageable pageable) {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(8, TimeUnit.HOURS))
                .body(animalService.getAnimalsForSale(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveAnimal(@RequestPart("animal") String animalJson, @RequestPart("photos") MultipartFile[] photos) throws JsonProcessingException {

        animalService.saveAnimal(animalJson, photos);
        return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESSFUL_SAVE_ANIMAL);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }


}
