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

        if (e.getCodePoint() == COLOR_ALREADY_EXISTS.getCode()) {
            return ResponseEntity.internalServerError().body(COLOR_ALREADY_EXISTS.getMessage());
        }
        if (e.getCodePoint() == COLOR_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(COLOR_NOT_FOUND.getMessage());
        }
        if (e.getCodePoint() == PHOTO_ALREADY_EXISTS.getCode()) {
            return ResponseEntity.internalServerError().body(PHOTO_ALREADY_EXISTS.getMessage());
        }
        if (e.getCodePoint() == CORRUPTED_PHOTO_FILE.getCode()) {
            return ResponseEntity.internalServerError().body(CORRUPTED_PHOTO_FILE.getMessage());
        }
        if (e.getCodePoint() == FILED_TO_SAVE_PHOTO_ON_DISK.getCode()) {
            return ResponseEntity.internalServerError().body(FILED_TO_SAVE_PHOTO_ON_DISK.getMessage());
        }
        if (e.getCodePoint() == ANIMAL_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(ANIMAL_NOT_FOUND.getMessage());
        }
        if (e.getCodePoint() == COLOR_NOT_FOUND.getCode()) {
            return ResponseEntity.internalServerError().body(COLOR_NOT_FOUND.getMessage());
        }



    return ResponseEntity.badRequest().body(UNEXPECTED_ERROR);
    }
}
