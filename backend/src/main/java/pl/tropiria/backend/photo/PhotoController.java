package pl.tropiria.backend.photo;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.PHOTO;

@RestController
@AllArgsConstructor
@RequestMapping(PHOTO)
public class PhotoController {

    private static final  String CONTENT_TYPE = "Content-Type";

    private static final  String IMAGE_PNG = "image/png";

    private final PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<PhotoDto>> getAllPhoto() {
        return ResponseEntity.ok(photoService.getAllPhoto());
    }

    @PostMapping
    public ResponseEntity savePhoto(@RequestParam("photo") MultipartFile photo){
        PhotoDto photoDto = photoService.savePhoto(photo);
        return ResponseEntity.created(URI.create(PHOTO + "/" + photoDto.getPhotoName())).body(photoDto);
    }

    @GetMapping("/{filename}")
    public ResponseEntity getPhoto(@PathVariable String filename) {
        return ResponseEntity.ok().header(CONTENT_TYPE, IMAGE_PNG).body(photoService.getPhoto(filename));
    }

}
