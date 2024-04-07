package pl.tropiria.backend.photos;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.tropiria.backend.utilites.PhotosDtoMapper;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import static pl.tropiria.backend.config.constants.ErrorsConstant.FAIL_TO_SAVE_PHOTO_ON_DISK;
import static pl.tropiria.backend.config.constants.PhotosConstant.PHOTOS_PATH;
import static pl.tropiria.backend.utilites.SHAEncoder.encode;

@Service
@AllArgsConstructor
public class PhotosService {

    private final PhotosRepository photosRepository;

    private static String photoName;

    private static String photoPath;

    private static PhotosDto photosDto;

    public List<PhotosDto> getPhotos() {
        return photosRepository
                .findAll()
                .stream()
                .map(PhotosDtoMapper::map)
                .toList();
    }


    public List<Photos> savePhoto(MultipartFile[] photos) {

        List<Photos> photosList = new ArrayList<>();
        for (MultipartFile photo : photos) {
            setPhotoName(photo);
            setPhotoPath();
            setPhotosDto();
            savePhotoToDatabase(photosDto);
            savePhotoOnDisk(photo);
            photosList.add(photosRepository.findByPhotoPath(photoPath));
        }
        return photosList;
    }

    public void deletePhotoFromDisk(PhotosDto photosDto){
        File file = new File(photosDto.getPhotoPath());
        if (file.exists()) {
            file.delete();
        }
    }

    private void savePhotoOnDisk(MultipartFile photo) {
        try {
            photo.transferTo(new File(photoPath));
        } catch (IOException e) {
            deletePhotoPathFromDatabase(photosDto);
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_ON_DISK.getCode());
        }
    }

    private void savePhotoToDatabase(PhotosDto photosDto){
        if (ifPhotoPathExists(photosDto.getPhotoPath())) {
            throw new IllegalFormatCodePointException(FAIL_TO_SAVE_PHOTO_ON_DISK.getCode());
        }
        photosRepository.save(PhotosDtoMapper.map(photosDto));
    }

    private boolean ifPhotoPathExists(String photoPath) {
        return photosRepository.findByPhotoPath(photoPath) != null;
    }

    private void deletePhotoPathFromDatabase(PhotosDto photosDto){
        photosRepository.delete(PhotosDtoMapper.map(photosDto));
    }



    private static void setPhotoName(MultipartFile photo) {
        photoName = encode(photo);
    }

    private static void setPhotoPath() {
        photoPath = PHOTOS_PATH.getPath() + File.separator + photoName;
    }

    private static void setPhotosDto() {
        photosDto = new PhotosDto(photoPath);

    }
}
