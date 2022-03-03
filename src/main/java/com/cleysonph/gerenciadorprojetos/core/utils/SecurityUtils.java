package com.cleysonph.gerenciadorprojetos.core.utils;

import com.cleysonph.gerenciadorprojetos.core.models.Usuario.Role;
import com.cleysonph.gerenciadorprojetos.core.services.auth.dtos.AuthenticatedUser;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    public Authentication getAuthentication() {
        return getContext().getAuthentication();
    }

    public void setAuthentication(Authentication authentication) {
        getContext().setAuthentication(authentication);
    }

    public boolean isAuthenticated() {
        var authentication = getAuthentication();
        return authentication != null
            && !(authentication instanceof AnonymousAuthenticationToken)
            && authentication.isAuthenticated();
    }

    public boolean isUnauthenticated() {
        return !isAuthenticated();
    }

    public boolean hasAuthority(Role role) {
        return isAuthenticated()
            && getAuthentication().getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals(role.name()));
    }

    public boolean isAdmin() {
        return hasAuthority(Role.ADMIN);
    }

    public boolean isUsuario() {
        return hasAuthority(Role.USUARIO);
    }

    public boolean isGerente() {
        return hasAuthority(Role.GERENTE);
    }

    public String getAuthenticatedName() {
        return getAuthentication().getName();
    }

    public AuthenticatedUser getAuthenticatedUser() {
        var principal = getAuthentication().getPrincipal();
        if (principal instanceof AuthenticatedUser) {
            return (AuthenticatedUser) principal;
        }
        return null;
    }

}
