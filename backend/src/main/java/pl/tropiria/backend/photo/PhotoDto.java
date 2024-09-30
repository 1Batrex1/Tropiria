package pl.tropiria.backend.photo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotoDto {

    private long id;
    private String photoName;

    public PhotoDto(String photoName) {
        this.photoName = photoName;
    }

    public static PhotoDto toDto(Photo photo) {
        return PhotoDto.builder()
                .id(photo.getId())
                .photoName(photo.getPhotoName())
                .build();
    }

    public static Photo toEntity(PhotoDto photoDto) {
        return Photo.builder()
                .id(photoDto.getId())
                .photoName(photoDto.getPhotoName())
                .build();
    }
}
