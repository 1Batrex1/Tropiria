package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SHAConstant {
    SHA3_512("SHA3-512",".png");

    private final String algorithm;
    private final String extension;


}
