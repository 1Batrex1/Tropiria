package pl.tropiria.backend.morph;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.MORPH;


@RestController
@AllArgsConstructor
@RequestMapping(MORPH)
public class MorphController {

    private final MorphService morphService;

    @GetMapping
    public ResponseEntity<List<MorphDto>> getMorphs() {
        return ResponseEntity.ok(morphService.getColors());
    }

    @PostMapping
    public ResponseEntity<?> saveMorphs(MorphDto morphDto) {
        morphService.saveColor(morphDto);
        return ResponseEntity.ok().build();
    }

}
