
package com.lorenzo.api_imoveis.security;

import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.repository.UsersRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository repository;

    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.repository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users usuario = repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    return new UserDetailsImpl(usuario);
}
}
