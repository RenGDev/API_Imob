
package com.lorenzo.api_imoveis.security;

import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.repository.UsersRepository;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository repository;

    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.repository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas"));
        
        List<SimpleGrantedAuthority> authorities = Arrays.asList(
            new SimpleGrantedAuthority("ROLE_USER")
        );
        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities 
        );
    }
}
