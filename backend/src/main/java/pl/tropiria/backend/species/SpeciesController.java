package pl.tropiria.backend.species;


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
}
