package pl.tropiria.backend.config.constants;


import java.util.IllegalFormatCodePointException;

public class EndpointConstant {

    private EndpointConstant() {
        throw new IllegalFormatCodePointException(ErrorsConstant.UTILITY_CLASS.getCode());
    }


    public static final String SPECIES = "/species";

    public static final String MORPH = "/morph";

    public static final String PHOTOS = "/photos";

    public static final String ANIMALS = "/animals";

    public static final String LOGIN = "/login";

}
