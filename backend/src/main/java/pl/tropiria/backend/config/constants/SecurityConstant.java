package pl.tropiria.backend.config.constants;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.UTILITY_CLASS;

public class SecurityConstant {

    private SecurityConstant() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.CODE);
    }

    public static final String JWT_HEADER = "Authorization";

    //Your secret key
    public static final String JWT_SECRET = "hghfghfghfg465456465465464585fjhgo8969869ut64645354hfghfggfhghfhffr7564764567464";

    public static final String JWT_NAME = "JWT_TOKEN";

    public static final String ISSUER = "Tropiria";

    public static final String USERNAME = "username";

    public static final String AUTHORITIES = "authorities";

    public static final long EXPIRATION_TIME = 30000000;
}