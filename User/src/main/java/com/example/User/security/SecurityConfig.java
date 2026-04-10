package com.example.User.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            GatewayHeaderAuthFilter gatewayHeaderAuthFilter
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // ✅ Allow health checks / metrics
                        .requestMatchers("/actuator/**",
                                       "/swagger-ui/**",
                                        "/v3/api-docs/**").permitAll()

                        // ✅ Example role-based protection
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )

                // ✅ Register gateway header filter
                .addFilterBefore(
                        gatewayHeaderAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
