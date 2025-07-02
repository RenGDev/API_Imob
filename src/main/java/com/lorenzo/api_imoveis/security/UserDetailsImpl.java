package com.lorenzo.api_imoveis.security;

import com.lorenzo.api_imoveis.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails {

    private final Users usuario;

    public UserDetailsImpl(Users usuario) {
        this.usuario = usuario;
    }

    public Users getUsuario() {
        return usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()));
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // ajuste conforme sua regra
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // ajuste conforme sua regra
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // ajuste conforme sua regra
    }

    @Override
    public boolean isEnabled() {
        return true; // ajuste conforme sua regra
    }
}
