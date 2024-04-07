package pl.tropiria.backend.color;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.COLORS;


@RestController
@AllArgsConstructor
@RequestMapping(COLORS)
public class ColorController {

    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<List<ColorDto>> getColors() {
        return ResponseEntity.ok(colorService.getColors());
    }

    @PostMapping
    public ResponseEntity<?> saveColor(ColorDto colorDto) {
        colorService.saveColor(colorDto);
        return ResponseEntity.ok().build();
    }

}
