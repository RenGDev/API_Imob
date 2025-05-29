package com.lorenzo.api_imoveis.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.exceptions.InvalidCredentialsException;
import com.lorenzo.api_imoveis.repository.UsersRepository;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    
    private final UsersRepository repository;

    public LoginService(UsersRepository repository) {
        this.repository = repository;
    }

    public Users login(String email, String password) throws InvalidCredentialsException {
        logger.info("Tentativa de login para email: {}", email);
        
        Users user = repository.findByEmail(email)
            .orElseThrow(() -> {
                logger.error("Usuário não encontrado para email: {}", email);
                return new InvalidCredentialsException("Credenciais inválidas");
            });

        if (!BCrypt.checkpw(password, user.getPassword())) {
            logger.error("Senha incorreta para usuário: {}", email);
            throw new InvalidCredentialsException("Credenciais inválidas");
        }
        
        logger.info("Login bem-sucedido para usuário: {}", email);
        return user;
    }
}  
