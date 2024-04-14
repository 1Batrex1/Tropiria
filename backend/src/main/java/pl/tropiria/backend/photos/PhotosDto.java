package pl.tropiria.backend.photos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotosDto {

    private long id;
    private String photoPath;

    public PhotosDto(String photoPath) {
        this.photoPath = photoPath;
    }
}
