package pl.tropiria.backend.config.constants;

import java.util.IllegalFormatCodePointException;

import static pl.tropiria.backend.config.constants.ErrorsConstant.UTILITY_CLASS;

public class SecurityConstant {

    private SecurityConstant() {
        throw new IllegalFormatCodePointException(UTILITY_CLASS.CODE);
    }

    public static final String JWT_HEADER = "Authorization";

    public static String JWT_SECRET;

    public static final String JWT_NAME = "JWT_TOKEN";

    public static final String ISSUER = "Tropiria";

    public static final String USERNAME = "username";

    public static final String AUTHORITIES = "authorities";

    public static final long EXPIRATION_TIME = 30000000;

    public static void setJwtSecret(String jwtSecret) {
        JWT_SECRET = jwtSecret;
    }
}