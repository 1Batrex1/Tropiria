package pl.tropiria.backend.species;


import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.SPECIES;

@RestController
@AllArgsConstructor
@RequestMapping(SPECIES)
public class SpeciesController {

    private final static String SUCCESSFUL_SAVE_SPECIES = "spiecies saved";

    private SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<List<SpeciesDto>> getSpecies() {
        return ResponseEntity.ok(speciesService.getSpecies());
    }

    @PostMapping
    public ResponseEntity<?> saveSpecies(@RequestParam("species") String speciesName) {
        speciesService.saveSpecies(new SpeciesDto(speciesName));
        return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESSFUL_SAVE_SPECIES);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpeciesDto> updateSpecies(@PathVariable Long id, @RequestParam("species") String speciesName) {
        return ResponseEntity.ok(speciesService.updateSpecies(id, new SpeciesDto(speciesName)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecies(@PathVariable Long id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.ok().build();
    }
}
