package pl.tropiria.backend.photo;

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

import static pl.tropiria.backend.config.constants.ErrorsConstant.*;
import static pl.tropiria.backend.config.constants.PhotosConstant.*;
import static pl.tropiria.backend.utilites.SHAEncoder.encode;

@Service
@AllArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    private static final Path directoryPath = Paths.get(PHOTOS_DIR).toAbsolutePath().normalize();

    public List<PhotoDto> getAllPhoto() {
        return photoRepository.findAll().stream()
                .map(PhotoDto::toDto)
                .toList();
    }


    public PhotoDto savePhoto(MultipartFile photo) {

        return savePhotoToDatabase(photo);
    }

    public Resource getPhoto(String filename) {
        try {
            Path filePath = directoryPath.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new IllegalFormatCodePointException(FAILED_TO_LOAD_PHOTO.CODE);
            }
        } catch (MalformedURLException e) {
            throw new IllegalFormatCodePointException(FAILED_TO_LOAD_PHOTO.CODE);
        }
    }


    private PhotoDto savePhotoToDatabase(MultipartFile photoFile) {

        String photoName = encode(photoFile);

        PhotoDto photoDto = new PhotoDto(photoName);

        if (ifPhotoNameExists(photoDto.getPhotoName())) {
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_PATH_TO_DATABASE.CODE);
        }
        Photo photo = photoRepository.save(PhotoDto.toEntity(photoDto));

        savePhotoOnDisk(photoFile, photo);

        return PhotoDto.toDto(photo);

    }


    public List<PhotoDto> saveMultiplePhotoInDataBase(MultipartFile[] photoMultipartList) {

        List<PhotoDto> photoList = new ArrayList<>();

        for (MultipartFile photo : photoMultipartList) {
            photoList.add(savePhotoToDatabase(photo));
        }
        return photoList;
    }



    private boolean ifPhotoNameExists(String photoName) {
        return photoRepository.findByPhotoName(photoName) != null;
    }

    private void savePhotoOnDisk(MultipartFile photoFile, Photo photo) {
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = directoryPath.resolve(photo.getPhotoName()).normalize();
            photoFile.transferTo(filePath);
        } catch (IOException e) {
            photoRepository.delete(photo);
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_ON_DISK.CODE);
        }
    }

    public void deletePhoto(PhotoDto photoDto) {
        deletePhotoNameFromDatabase(photoDto);
        deletePhotoFromDisk(photoDto);
    }

    private void deletePhotoNameFromDatabase(PhotoDto photoDto) {
        photoRepository.delete(PhotoDto.toEntity(photoDto));
    }


    private void deletePhotoFromDisk(PhotoDto photoDto) {
        try {
            Files.delete(Paths.get(PHOTOS_DIR + File.separator + photoDto.getPhotoName()));
        } catch (IOException e) {
            throw new IllegalFormatCodePointException(FAIL_TO_DELETE_PHOTO.CODE);
        }
    }

    public void checkPhotosLimit(MultipartFile[] photoMultipartList) {
        if (photoMultipartList.length > PHOTO_MAX_LIMIT || photoMultipartList.length < PHOTO_MIN_LIMIT) {
            throw new IllegalFormatCodePointException(PHOTO_LIMIT_EXCEEDED.CODE);
        }
    }


}
