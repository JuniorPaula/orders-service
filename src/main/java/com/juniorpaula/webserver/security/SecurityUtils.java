package com.juniorpaula.webserver.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.juniorpaula.webserver.entities.User;

public class SecurityUtils {

  public static User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
        return null; // ou lança exceção, dependendo do caso
    }

    return (User) authentication.getPrincipal();
  }
}
