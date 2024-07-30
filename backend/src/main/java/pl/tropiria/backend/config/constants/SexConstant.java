package pl.tropiria.backend.config.constants;

import lombok.AllArgsConstructor;

import java.util.IllegalFormatCodePointException;

public class SexConstant {

    private SexConstant() {
        throw new IllegalFormatCodePointException(ErrorsConstant.UTILITY_CLASS.CODE);
    }

    public static int UNKNOWN = 0;

    public static int MALE = 1;

    public static int FEMALE = 2;


}
