package pl.tropiria.backend.photos;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.FAIL_TO_DELETE_PHOTO;
import static pl.tropiria.backend.config.constants.ErrorsConstant.FAIL_TO_SAVE_PHOTO_ON_DISK;
import static pl.tropiria.backend.config.constants.PhotosConstant.PHOTOS_DIR;
import static pl.tropiria.backend.utilites.SHAEncoder.encode;

@Service
@AllArgsConstructor
public class PhotosService {

    private final PhotosRepository photosRepository;


    private static final Path directoryPath =  Paths.get(PHOTOS_DIR).toAbsolutePath().normalize();

    private static String photoName;

    private static PhotosDto photosDto;

    public List<PhotosDto> getPhotos() {
        return photosRepository.findAll().stream()
                .map(photo -> PhotosDto.builder()
                        .id(photo.getId())
                        .photoName(photo.getPhotoName())
                        .build())
                .toList();
    }


    public List<Photos> savePhoto(MultipartFile[] photos) {

        List<Photos> photosList = new ArrayList<>();
        savePhotoProcedure(photos, photosList);
        return photosList;
    }

    public Resource getPhoto(String filename) {
        try {
            Path filePath = directoryPath.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new IllegalFormatCodePointException(FAIL_TO_DELETE_PHOTO.CODE);
            }
        } catch (MalformedURLException e) {
            throw new IllegalFormatCodePointException(FAIL_TO_DELETE_PHOTO.CODE);
        }
    }

    private void savePhotoProcedure(MultipartFile[] photos, List<Photos> photosList) {
        for (MultipartFile photo : photos) {
            setPhotoName(photo);
            setPhotosDto();
            savePhotoToDatabase(photosDto);
            savePhotoOnDisk(photo);
            photosList.add(photosRepository.findByPhotoName(photoName));
        }
    }

    private static void setPhotoName(MultipartFile photo) {
        photoName = encode(photo);
    }


    private static void setPhotosDto() {
        photosDto = new PhotosDto(photoName);
    }

    private void savePhotoToDatabase(PhotosDto photosDto) {
        if (ifPhotoNameExists(photosDto.getPhotoName())) {
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_ON_DISK.CODE);
        }
        photosRepository.save(Photos.builder()
                .id(photosDto.getId())
                .photoName(photosDto.getPhotoName())
                .build());

    }

    private boolean ifPhotoNameExists(String photoName) {
        return photosRepository.findByPhotoName(photoName) != null;
    }

    private void savePhotoOnDisk(MultipartFile photo) {
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = directoryPath.resolve(photoName).normalize();
            photo.transferTo(filePath);
        } catch (IOException e) {
            deletePhotoNameFromDatabase(photosDto);
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_ON_DISK.CODE);
        }
    }

    public void deletePhotoNameFromDatabase(PhotosDto photosDto) {
        photosRepository.delete(Photos.builder()
                .id(photosDto.getId())
                .photoName(photosDto.getPhotoName())
                .build());
    }


    public void deletePhotoFromDisk(PhotosDto photosDto) {
        try {
            Files.delete(Paths.get(PHOTOS_DIR + File.separator + photosDto.getPhotoName()));
        } catch (IOException e) {
            throw new IllegalFormatCodePointException(FAIL_TO_DELETE_PHOTO.CODE);
        }
    }

}
