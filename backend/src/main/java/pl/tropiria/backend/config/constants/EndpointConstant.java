package pl.tropiria.backend.config.constants;


import java.util.IllegalFormatCodePointException;

public class EndpointConstant {

    private EndpointConstant() {
        throw new IllegalFormatCodePointException(ErrorsConstant.UTILITY_CLASS.CODE);
    }


    public static final String SPECIES = "/species";

    public static final String MORPH = "/morph";

    public static final String PHOTO = "/photo";

    public static final String ANIMALS = "/animals";

    public static final String LOGIN = "/login";

    public static final String CSRF = "/csrf";

    public static final String JWT = "/jwt";

}
