package pl.tropiria.backend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.*;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler  {

    private static final String UNEXPECTED_ERROR = "Unexpected error";

    private static final String BAD_CREDENTIALS = "Bad credentials";

    @ExceptionHandler(IllegalFormatCodePointException.class)
    protected ResponseEntity<String> handleIllegalFormatCodePointException(IllegalFormatCodePointException e) {
        if (e.getCodePoint() == NO_ALGORITHM.CODE) {
            return ResponseEntity.internalServerError().body(NO_ALGORITHM.MESSAGE);
        }
        if (e.getCodePoint() == SPECIES_ALREADY_EXISTS.CODE) {
            return ResponseEntity.internalServerError().body(SPECIES_ALREADY_EXISTS.MESSAGE);
        }
        if (e.getCodePoint() == SPECIES_NOT_FOUND.CODE) {
            return ResponseEntity.internalServerError().body(SPECIES_NOT_FOUND.MESSAGE);
        }
        if (e.getCodePoint() == MORPH_ALREADY_EXISTS.CODE) {
            return ResponseEntity.internalServerError().body(MORPH_ALREADY_EXISTS.MESSAGE);
        }
        if (e.getCodePoint() == MORPH_NOT_FOUND.CODE) {
            return ResponseEntity.internalServerError().body(MORPH_NOT_FOUND.MESSAGE);
        }
        if (e.getCodePoint() == PHOTO_ALREADY_EXISTS.CODE) {
            return ResponseEntity.internalServerError().body(PHOTO_ALREADY_EXISTS.MESSAGE);
        }
        if (e.getCodePoint() == CORRUPTED_PHOTO_FILE.CODE) {
            return ResponseEntity.internalServerError().body(CORRUPTED_PHOTO_FILE.MESSAGE);
        }
        if (e.getCodePoint() == FAIL_TO_SAVE_PHOTO_ON_DISK.CODE) {
            return ResponseEntity.internalServerError().body(FAIL_TO_SAVE_PHOTO_ON_DISK.MESSAGE);
        }
        if (e.getCodePoint() == ANIMAL_NOT_FOUND.CODE) {
            return ResponseEntity.internalServerError().body(ANIMAL_NOT_FOUND.MESSAGE);
        }
        if (e.getCodePoint() == ANIMAL_NOT_FOR_SALE.CODE) {
            return ResponseEntity.internalServerError().body(ANIMAL_NOT_FOR_SALE.MESSAGE);
        }
        if (e.getCodePoint() == MORPH_NOT_FOUND.CODE) {
            return ResponseEntity.internalServerError().body(MORPH_NOT_FOUND.MESSAGE);
        }
        if (e.getCodePoint() == FAIL_TO_SAVE_PHOTO_ON_DISK.CODE) {
            return ResponseEntity.internalServerError().body(FAIL_TO_SAVE_PHOTO_PATH_TO_DATABASE.MESSAGE);
        }
        if (e.getCodePoint() == INVALID_RESERVATION_STATUS.CODE) {
            return ResponseEntity.badRequest().body(INVALID_RESERVATION_STATUS.MESSAGE);
        }
        if (e.getCodePoint() == PARENTS_NOT_FOUND.CODE) {
            return ResponseEntity.internalServerError().body(PARENTS_NOT_FOUND.MESSAGE);
        }
        if (e.getCodePoint() == INVALID_PASSWORD.CODE) {
            return ResponseEntity.internalServerError().body(INVALID_PASSWORD.MESSAGE);
        }
        if (e.getCodePoint() == INVALID_TOKEN.CODE) {
            return ResponseEntity.internalServerError().body(INVALID_TOKEN.MESSAGE);
        }
        if (e.getCodePoint() == INVALID_JSON.CODE) {
            return ResponseEntity.internalServerError().body(INVALID_JSON.MESSAGE);
        }
        if (e.getCodePoint() == INVALID_SEX.CODE) {
            return ResponseEntity.badRequest().body(INVALID_SEX.MESSAGE);
        }
        if (e.getCodePoint() == FAILED_TO_LOAD_PHOTO.CODE) {
            return ResponseEntity.internalServerError().body(FAILED_TO_LOAD_PHOTO.MESSAGE);
        }
        if (e.getCodePoint() == PHOTO_LIMIT_EXCEEDED.CODE) {
            return ResponseEntity.badRequest().body(PHOTO_LIMIT_EXCEEDED.MESSAGE);
        }
        return ResponseEntity.badRequest().body(UNEXPECTED_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        return ResponseEntity.internalServerError().body(UNEXPECTED_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.badRequest().body(BAD_CREDENTIALS);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(UNEXPECTED_ERROR);
    }
}
