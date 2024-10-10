package pl.tropiria.backend.config.security;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.tropiria.backend.config.constants.SecurityConstant;

@Getter
@Component
public class JWTSecretHandler {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostConstruct
    public void init() {
        SecurityConstant.setJwtSecret(jwtSecret);
    }

}