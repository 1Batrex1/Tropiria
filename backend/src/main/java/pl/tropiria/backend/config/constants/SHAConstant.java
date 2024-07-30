package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SHAConstant {
    SHA3_512("SHA3-512",".png");

    public final String ALGORITHM;
    public final String EXTENSION;
}
