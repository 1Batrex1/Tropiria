package pl.tropiria.backend.config.constants;

import java.io.File;
import java.util.IllegalFormatCodePointException;

public class PhotoConstant {

    private PhotoConstant() {
        throw new IllegalFormatCodePointException(ErrorsConstant.UTILITY_CLASS.CODE);
    }

    public static final String PHOTO_DIR = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "photos";

    public static final Integer PHOTO_MAX_LIMIT = 5;

    public static final Integer PHOTO_MIN_LIMIT = 1;

}
