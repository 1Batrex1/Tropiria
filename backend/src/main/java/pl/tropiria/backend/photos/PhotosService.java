package pl.tropiria.backend.photos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.FAIL_TO_DELETE_PHOTO;
import static pl.tropiria.backend.config.constants.ErrorsConstant.FAIL_TO_SAVE_PHOTO_ON_DISK;
import static pl.tropiria.backend.utilites.SHAEncoder.encode;

@Service
@AllArgsConstructor
public class PhotosService {

    private final PhotosRepository photosRepository;

    private static final String PHOTOS_PATH = System.getProperty("user.dir").replace("\\backend", "") + File.separator + "photos";

    private static String photoName;

    private static String photoPath;

    private static PhotosDto photosDto;

    public List<PhotosDto> getPhotos() {
        return photosRepository.findAll().stream()
                .map(photo -> PhotosDto.builder()
                        .id(photo.getId())
                        .photoPath(photo.getPhotoPath())
                        .build())
                .toList();
    }


    public List<Photos> savePhoto(MultipartFile[] photos) {

        List<Photos> photosList = new ArrayList<>();
        savePhotoProcedure(photos, photosList);
        return photosList;
    }

    private void savePhotoProcedure(MultipartFile[] photos, List<Photos> photosList) {
        for (MultipartFile photo : photos) {
            setPhotoName(photo);
            setPhotoPath();
            setPhotosDto();
            savePhotoToDatabase(photosDto);
            savePhotoOnDisk(photo);
            photosList.add(photosRepository.findByPhotoPath(photoPath));
        }
    }

    private static void setPhotoName(MultipartFile photo) {
        photoName = encode(photo);
    }

    private static void setPhotoPath() {
        photoPath = PHOTOS_PATH + File.separator + photoName;
    }

    private static void setPhotosDto() {
        photosDto = new PhotosDto(photoPath);
    }

    private void savePhotoToDatabase(PhotosDto photosDto) {
        if (ifPhotoPathExists(photosDto.getPhotoPath())) {
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_ON_DISK.CODE);
        }
        photosRepository.save(Photos.builder()
                .id(photosDto.getId())
                .photoPath(photosDto.getPhotoPath())
                .build());

    }

    private boolean ifPhotoPathExists(String photoPath) {
        return photosRepository.findByPhotoPath(photoPath) != null;
    }

    private void savePhotoOnDisk(MultipartFile photo) {
        try {
            photo.transferTo(new File(photoPath));
        } catch (IOException e) {
            deletePhotoPathFromDatabase(photosDto);
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_ON_DISK.CODE);
        }
    }

    public void deletePhotoPathFromDatabase(PhotosDto photosDto) {
        photosRepository.delete(Photos.builder()
                .id(photosDto.getId())
                .photoPath(photosDto.getPhotoPath())
                .build());
    }


    public void deletePhotoFromDisk(PhotosDto photosDto) {
        try {
            Files.delete(Path.of(photosDto.getPhotoPath()));
        } catch (IOException e) {
            throw new IllegalFormatCodePointException(FAIL_TO_DELETE_PHOTO.CODE);
        }
    }

}
