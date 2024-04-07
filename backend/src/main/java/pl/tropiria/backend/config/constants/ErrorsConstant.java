package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorsConstant {
    SPECIES_ALREADY_EXISTS(0, "Species already exists"),
    SPECIES_NOT_FOUND(1, "Species not found"),
    COLOR_ALREADY_EXISTS(2, "Color already exists"),
    COLOR_NOT_FOUND(3, "Color not found"),
    PHOTO_ALREADY_EXISTS(4, "Photo already exists"),
    FILED_TO_SAVE_PHOTO_ON_DISK(5, "Failed to save photo on disk"),
    FILED_TO_SAVE_PHOTO_PATH_TO_DATABASE(6, "Failed to save photo path to database"),
    CORRUPTED_PHOTO_FILE(7, "Corrupted photo file"),
    NO_ALGORITHM(8, "No such algorithm"),
    UTILITY_CLASS(9, "Utility class"),
    ANIMAL_NOT_FOUND(10, "Animal not found"),
    PARENTS_NOT_FOUND(11, "Parents not found"),
    JSON_PARSE_ERROR(12, "Json parse error"),
    INVALID_RESERVATION_STATUS(13, "Invalid reservation status"),
    ;
    private final int code;
    private final String message;
}

