package ru.kpfu.itis.kuzmin.skillshare.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtTokenAuthentication implements Authentication {

    private final Long userId;
    private final List<SimpleGrantedAuthority> roles;
    private boolean authenticated;

    public JwtTokenAuthentication(Long userId, List<SimpleGrantedAuthority> roles) {
        if (userId == null || roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.userId = userId;
        this.roles = roles;
        this.authenticated = true;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}
