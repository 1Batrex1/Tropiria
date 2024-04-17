package pl.tropiria.backend.photos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotosDto {

    private long id;
    private String photoPath;

    public PhotosDto(String photoPath) {
        this.photoPath = photoPath;
    }
}
