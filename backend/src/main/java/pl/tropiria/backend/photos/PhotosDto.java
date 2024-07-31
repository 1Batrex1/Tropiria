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
    private String photoName;

    public PhotosDto(String photoName) {
        this.photoName = photoName;
    }
}
