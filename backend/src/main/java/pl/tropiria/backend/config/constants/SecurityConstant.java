package pl.tropiria.backend.config.constants;

public interface SecurityConstant {

    public static final String JWT_HEADER = "Authorization";

    //Your secret key
    public static final String JWT_SECRET = "";

    public static final String JWT_NAME = "JWT_TOKEN";

    public static final String ISSUER = "Tropiria";

    public static final String USERNAME = "username";

    public static final String AUTHORITIES = "authorities";

    public static final long EXPIRATION_TIME = 30000000;
}