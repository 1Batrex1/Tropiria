package pl.tropiria.backend.config.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.tropiria.backend.config.constants.SecurityConstant;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import static pl.tropiria.backend.config.constants.EndpointConstant.LOGIN;
import static pl.tropiria.backend.config.constants.SecurityConstant.*;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    private static final Log logger = LogFactory.getLog(JWTTokenGeneratorFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
            String token = Jwts.builder()
                    .setIssuer(ISSUER)
                    .setSubject(JWT_NAME)
                    .claim(USERNAME, authentication.getName())
                    .claim(AUTHORITIES, populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plusMillis(EXPIRATION_TIME)))
                    .signWith(key).compact();
            response.setHeader(JWT_HEADER, token);
        }
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        logger.info("Request path: " + request.getServletPath());
        return !request.getServletPath().equals(LOGIN);

    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
