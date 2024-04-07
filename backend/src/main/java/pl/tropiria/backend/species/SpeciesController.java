package pl.tropiria.backend.species;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.SPECIES;

@RestController
@AllArgsConstructor
@RequestMapping(SPECIES)
public class SpeciesController {

    private final SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<List<SpeciesDto>> getSpecies() {
        return ResponseEntity.ok(speciesService.getSpecies());
    }

    @PostMapping
    public ResponseEntity<?> saveSpecies(@RequestParam("species") String speciesName) {
        speciesService.saveSpecies(new SpeciesDto(speciesName));
        return ResponseEntity.ok().build();
    }
}
