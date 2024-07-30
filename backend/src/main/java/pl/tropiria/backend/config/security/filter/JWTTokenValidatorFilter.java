package pl.tropiria.backend.config.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.tropiria.backend.config.constants.SecurityConstant;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static pl.tropiria.backend.config.constants.EndpointConstant.LOGIN;
import static pl.tropiria.backend.config.constants.ErrorsConstant.INVALID_TOKEN;
import static pl.tropiria.backend.config.constants.SecurityConstant.JWT_HEADER;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader(JWT_HEADER);
        if (jwtToken != null) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwtToken)
                        .getBody();
                String username = claims.get(SecurityConstant.USERNAME, String.class);
                String authorities = claims.get(SecurityConstant.AUTHORITIES, String.class);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception e) {
                throw new BadCredentialsException(INVALID_TOKEN.MESSAGE);
            }


    }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        logger.info("Request path: " + request.getServletPath());
        return request.getServletPath().equals(LOGIN);
    }
}
