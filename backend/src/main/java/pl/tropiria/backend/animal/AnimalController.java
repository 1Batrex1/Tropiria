package pl.tropiria.backend.animal;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.ANIMALS;

@AllArgsConstructor
@RestController
@RequestMapping(ANIMALS)
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAnimals() {
        return ResponseEntity.ok(animalService.getAnimals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveAnimal(@RequestParam("description") String description,
                                        @RequestParam("sex") Integer sex,
                                        @RequestParam("dateOfBirth") String dateOfBirth,
                                        @RequestParam("species") String speciesName,
                                        @RequestParam("morphs") List<String> morphs,
                                        @RequestParam("photos") MultipartFile[] photos,
                                        @RequestParam(value = "name",required = false) String name,
                                        @RequestParam(value = "price",required = false) Long price,
                                        @RequestParam(value = "reservationStatus",required = false) String reservationStatus,
                                        @RequestParam(value = "parents",required = false) List<String> parents) {
        animalService.saveAnimal(description,sex,dateOfBirth,speciesName,morphs,photos,name,price,reservationStatus,parents);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }


}
