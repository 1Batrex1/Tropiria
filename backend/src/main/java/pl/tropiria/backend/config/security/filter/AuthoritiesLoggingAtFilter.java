package pl.tropiria.backend.config.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthoritiesLoggingAtFilter extends OncePerRequestFilter {

    private static final Log logger = LogFactory.getLog(AuthoritiesLoggingAtFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Authenticating user");
        filterChain.doFilter(request, response);
    }
}
