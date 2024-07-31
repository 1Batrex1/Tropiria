package pl.tropiria.backend.config.constants;

import java.util.IllegalFormatCodePointException;

public class SexConstant {

    private SexConstant() {
        throw new IllegalFormatCodePointException(ErrorsConstant.UTILITY_CLASS.CODE);
    }

    public static final int UNKNOWN = 0;

    public static final int MALE = 1;

    public static final int FEMALE = 2;


}
