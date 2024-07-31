package pl.tropiria.backend.photos;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
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

    private static final  String SUCCESSFUL_SAVE_PHOTO = "photo saved to disk";

    private static final  String CONTENT_TYPE = "Content-Type";

    private static final  String IMAGE_PNG = "image/png";

    private final PhotosService photosService;

    @GetMapping
    public ResponseEntity<List<PhotosDto>> getPhotos() {
        return ResponseEntity.ok(photosService.getPhotos());
    }

    @PostMapping
    public ResponseEntity savePhoto(@RequestParam("photos") MultipartFile[] photos){
        photosService.savePhoto(photos);
        return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESSFUL_SAVE_PHOTO);
    }

    @GetMapping("/{filename}")
    public ResponseEntity getPhoto(@PathVariable String filename) {
        return ResponseEntity.ok().header(CONTENT_TYPE, IMAGE_PNG).body(photosService.getPhoto(filename));
    }

}
