package pl.tropiria.backend.morph;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.MORPH;


@RestController
@AllArgsConstructor
@RequestMapping(MORPH)
public class MorphController {

    private final MorphService morphService;

    @GetMapping
    public ResponseEntity<List<MorphDto>> getMorphs() {
        return ResponseEntity.ok(morphService.getMorphs());
    }

    @PostMapping
    public ResponseEntity<?> saveMorphs(@RequestParam("morph") String morphName) throws URISyntaxException {
        MorphDto morphDto = morphService.saveMorph(new MorphDto(morphName));
        return ResponseEntity.created(
                        new URI(MORPH + "/" + morphDto.getId()))
                .body(morphDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MorphDto> updateMorph(@PathVariable Long id, @RequestParam("morph") String morphName) {
        return ResponseEntity.ok(morphService.updateMorph(id, new MorphDto(morphName)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMorph(@PathVariable Long id) {
        morphService.deleteMorph(id);
        return ResponseEntity.ok().build();
    }

}
