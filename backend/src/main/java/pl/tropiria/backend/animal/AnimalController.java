package pl.tropiria.backend.animal;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<Page<AnimalDto>> getAllAnimals(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(animalService.getAnimals(pageable));
    }

    @GetMapping("/for-sale")
    public ResponseEntity<Page<AnimalDto>> getAnimalsForSale(Pageable pageable) {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(8, TimeUnit.HOURS))
                .body(animalService.getAnimalsForSale(pageable));
    }

    @GetMapping("/parents")
    public ResponseEntity<List<AnimalDto>> getParents() {
        return ResponseEntity.ok(animalService.getParents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AnimalDto> saveAnimal(@RequestPart("animal") String animalJson, @RequestPart("photoList") MultipartFile[] photoList) {

        animalService.saveAnimal(animalJson, photoList);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDto> updateReservationStatus(@PathVariable long id,@RequestPart("status") String status) {
        AnimalDto animalDto= animalService.updateReservationStatus(id, status);
        return ResponseEntity.ok().body(animalDto);
    }
}
