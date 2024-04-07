package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@Getter
@AllArgsConstructor
public enum PhotosConstant {

    PHOTOS_PATH(System.getProperty("user.dir").replace("\\backend", "") + File.separator + "photos");

    private final String path;


}
