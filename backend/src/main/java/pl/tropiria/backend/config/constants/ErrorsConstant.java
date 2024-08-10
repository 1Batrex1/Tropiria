package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorsConstant {
    SPECIES_ALREADY_EXISTS(0, "Species already exists"),
    SPECIES_NOT_FOUND(1, "Species not found"),
    MORPH_ALREADY_EXISTS(2, "Morph already exists"),
    MORPH_NOT_FOUND(3, "Morph not found"),
    PHOTO_ALREADY_EXISTS(4, "Photo already exists"),
    FAIL_TO_SAVE_PHOTO_ON_DISK(5, "Failed to save photo on disk"),
    FAIL_TO_SAVE_PHOTO_PATH_TO_DATABASE(6, "Failed to save photo path to database"),
    CORRUPTED_PHOTO_FILE(7, "Corrupted photo file"),
    NO_ALGORITHM(8, "No such algorithm"),
    UTILITY_CLASS(9, "Utility class"),
    ANIMAL_NOT_FOUND(10, "Animal not found"),
    PARENTS_NOT_FOUND(11, "Parents not found"),
    INVALID_RESERVATION_STATUS(13, "Invalid reservation status"),
    USER_NOT_FOUND(14,"User details not found"),
    INVALID_PASSWORD(15,"Invalid password"),
    INVALID_TOKEN(16,"Invalid token"),
    FAIL_TO_DELETE_PHOTO(17,"Failed to delete photo"),
    INVALID_JSON(18,"Invalid json"),
    INVALID_SEX(19,"Invalid sex"),
    FAILED_TO_LOAD_PHOTO(20,"Failed to load photo"),
    PHOTO_LIMIT_EXCEEDED(21,"Photo limit exceeded"),
    ;
    public final int CODE;
    public final String MESSAGE;
}

