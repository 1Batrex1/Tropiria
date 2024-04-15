package pl.tropiria.backend.config.security;

import lombok.AllArgsConstructor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static pl.tropiria.backend.config.constants.ErrorsConstant.INVALID_PASSWORD;

@AllArgsConstructor
@Component
public class TropiriaUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final TropiriaUserDetails userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private static final Log logger = LogFactory.getLog(TropiriaUsernamePasswordAuthenticationProvider.class);


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();

        String password = authentication.getCredentials().toString();

        UserDetails account = userDetailsService.loadUserByUsername(username);


        if (passwordEncoder.matches(password, account.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, account.getAuthorities());
        } else {
            throw new BadCredentialsException(INVALID_PASSWORD.getMessage());
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}