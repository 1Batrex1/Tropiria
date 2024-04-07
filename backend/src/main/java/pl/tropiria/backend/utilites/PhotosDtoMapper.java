package pl.tropiria.backend.utilites;

import pl.tropiria.backend.photos.Photos;
import pl.tropiria.backend.photos.PhotosDto;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.UTILITY_CLASS;

public class PhotosDtoMapper {

    private PhotosDtoMapper() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.getCode());
    }

    public static PhotosDto map(Photos photos) {
        return new PhotosDto(photos.getId(), photos.getPhotoPath());
    }

    public static Photos map(PhotosDto photosDto) {
        return new Photos(photosDto.getId(), photosDto.getPhotoPath());
    }
}
