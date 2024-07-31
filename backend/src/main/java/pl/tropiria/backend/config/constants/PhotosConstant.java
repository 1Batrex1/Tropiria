package pl.tropiria.backend.config.constants;

import java.io.File;
import java.util.IllegalFormatCodePointException;

public class PhotosConstant {

    private PhotosConstant() {
        throw new IllegalFormatCodePointException(ErrorsConstant.UTILITY_CLASS.CODE);
    }

    public static final String PHOTOS_DIR = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "photos";

}
