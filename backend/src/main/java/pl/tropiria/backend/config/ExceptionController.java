package pl.tropiria.backend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.*;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler  {

    private static final String UNEXPECTED_ERROR = "Unexpected error";

    @ExceptionHandler(IllegalFormatCodePointException.class)
    protected ResponseEntity<String> handleIllegalFormatCodePointException(IllegalFormatCodePointException e) {
        if (e.getCodePoint() == NO_ALGORITHM.getCode()) {
            return ResponseEntity.internalServerError().body(NO_ALGORITHM.getMessage());
        }
        if (e.getCodePoint() == SPECIES_ALREADY_EXISTS.getCode()) {
            return ResponseEntity.internalServerError().body(SPECIES_ALREADY_EXISTS.getMessage());
        }
        if (e.getCodePoint() == SPECIES_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(SPECIES_NOT_FOUND.getMessage());
        }
        if (e.getCodePoint() == MORPH_ALREADY_EXISTS.getCode()) {
            return ResponseEntity.internalServerError().body(MORPH_ALREADY_EXISTS.getMessage());
        }
        if (e.getCodePoint() == MORPH_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(MORPH_NOT_FOUND.getMessage());
        }
        if (e.getCodePoint() == PHOTO_ALREADY_EXISTS.getCode()) {
            return ResponseEntity.internalServerError().body(PHOTO_ALREADY_EXISTS.getMessage());
        }
        if (e.getCodePoint() == CORRUPTED_PHOTO_FILE.getCode()) {
            return ResponseEntity.internalServerError().body(CORRUPTED_PHOTO_FILE.getMessage());
        }
        if (e.getCodePoint() == FAIL_TO_SAVE_PHOTO_ON_DISK.getCode()) {
            return ResponseEntity.internalServerError().body(FAIL_TO_SAVE_PHOTO_ON_DISK.getMessage());
        }
        if (e.getCodePoint() == ANIMAL_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(ANIMAL_NOT_FOUND.getMessage());
        }
        if (e.getCodePoint() == MORPH_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(MORPH_NOT_FOUND.getMessage());
        }
        if (e.getCodePoint() == FAIL_TO_SAVE_PHOTO_ON_DISK.getCode()) {
            return ResponseEntity.internalServerError().body(FAIL_TO_SAVE_PHOTO_PATH_TO_DATABASE.getMessage());
        }
        if (e.getCodePoint() == INVALID_RESERVATION_STATUS.getCode()) {
            return ResponseEntity.badRequest().body(INVALID_RESERVATION_STATUS.getMessage());
        }
        if (e.getCodePoint() == JSON_PARSE_ERROR.getCode()) {
            return ResponseEntity.internalServerError().body(JSON_PARSE_ERROR.getMessage());
        }
        if (e.getCodePoint() == PARENTS_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(PARENTS_NOT_FOUND.getMessage());
        }




    return ResponseEntity.badRequest().body(UNEXPECTED_ERROR);
    }
}
