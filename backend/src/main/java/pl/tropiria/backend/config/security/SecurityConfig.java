package pl.tropiria.backend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import pl.tropiria.backend.config.security.filter.*;

import java.util.List;

import static pl.tropiria.backend.config.constants.EndpointConstant.*;
import static pl.tropiria.backend.config.constants.SecurityConstant.JWT_HEADER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ALLOWED_ORIGIN = "http://localhost:4200";

    private static final String ALLOWED_METHODS = "*";

    private static final String ALLOWED_HEADERS = "*";

    private static final Boolean ALLOW_CREDENTIALS = true;

    private static final Long MAX_AGE = 3600L;

    private static final String ADMIN_ROLE = "ADMIN";

    private static final String CSRF_NAME = "_csrf";


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName(CSRF_NAME);

        http
                .securityContext(securityContext ->
                        securityContext
                                .requireExplicitSave(false)
                )
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                )
                .cors(cors -> {
                    CorsConfigurationSource source = _ -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of(ALLOWED_ORIGIN));
                        config.setAllowedMethods(List.of(ALLOWED_METHODS));
                        config.setAllowCredentials(ALLOW_CREDENTIALS);
                        config.setAllowedHeaders(List.of(ALLOWED_HEADERS));
                        config.setExposedHeaders(List.of(JWT_HEADER));
                        config.setMaxAge(MAX_AGE);
                        return config;
                    };
                    cors.configurationSource(source);
                }).csrf(csrf -> csrf
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers(LOGIN)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )

                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(HttpMethod.GET, SPECIES).permitAll()
                                .requestMatchers(HttpMethod.GET, ANIMALS).permitAll()
                                .requestMatchers(HttpMethod.GET, MORPH).permitAll()
                                .requestMatchers(LOGIN).permitAll()
                                .anyRequest().hasRole(ADMIN_ROLE)

                ).httpBasic(Customizer.withDefaults());


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}