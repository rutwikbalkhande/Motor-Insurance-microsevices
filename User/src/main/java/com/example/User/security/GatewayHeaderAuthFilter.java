package com.example.User.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class GatewayHeaderAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String username = request.getHeader("X-USER-NAME");
        String role = request.getHeader("X-USER-ROLE");

        log.info("Incoming Gateway Headers → user: {}, role: {}", username, role);

        if (username != null && !username.isBlank()
                && role != null && !role.isBlank()
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            // ✅ Ensure role format
            String normalizedRole = role.toUpperCase();

            SimpleGrantedAuthority authority =
                    new SimpleGrantedAuthority(normalizedRole);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(authority)
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("SecurityContext set for user: {} with role: {}",
                    username, normalizedRole);
        }

        filterChain.doFilter(request, response);
    }
}