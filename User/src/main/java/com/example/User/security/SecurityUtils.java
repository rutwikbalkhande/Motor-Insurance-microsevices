package com.example.User.security;

import org.springframework.security.core.context.SecurityContextHolder;


    public class SecurityUtils {

        public static String getCurrentUser() {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }

        public static String getCurrentUsername() {
            return SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();
        }
    }
