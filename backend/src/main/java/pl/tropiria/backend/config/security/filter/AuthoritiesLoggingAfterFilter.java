package pl.tropiria.backend.config.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthoritiesLoggingAfterFilter extends OncePerRequestFilter {

    private static final Log logger = LogFactory.getLog(AuthoritiesLoggingAfterFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            logger.info("User: " + authentication.getName() + " has authorities: " + authentication.getAuthorities().toString());
        }

        doFilter(request, response, filterChain);
    }
}
