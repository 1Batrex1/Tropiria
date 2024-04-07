package pl.tropiria.backend.photos;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.PHOTOS;

@RestController
@AllArgsConstructor
@RequestMapping(PHOTOS)
@Profile("dev")
public class PhotosController {

    private final PhotosService photosService;

    @GetMapping
    public ResponseEntity<List<PhotosDto>> getPhotos() {
        return ResponseEntity.ok(photosService.getPhotos());
    }

    @PostMapping
    public ResponseEntity savePhoto(@RequestParam("photos") MultipartFile[] photos){
        photosService.savePhoto(photos);
        return ResponseEntity.ok().build();
    }

}
