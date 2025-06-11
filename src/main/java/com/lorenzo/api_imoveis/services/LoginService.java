package com.lorenzo.api_imoveis.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.lorenzo.api_imoveis.DTOs.LoginRequestDTO;
import com.lorenzo.api_imoveis.DTOs.LoginResponseDTO;
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

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO request, String token) throws InvalidCredentialsException {
        logger.info("Tentativa de login para email: {}", request.getEmail());
        
        Users user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> {
                logger.error("Usuário não encontrado para email: {}", request.getEmail());
                return new InvalidCredentialsException("Credenciais inválidas");
            });

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            logger.error("Senha incorreta para usuário: {}", request.getEmail());
            throw new InvalidCredentialsException("Credenciais inválidas");
        }
        
        LoginResponseDTO response = new LoginResponseDTO(user, token);
        logger.info("Login bem-sucedido para usuário: {}", request.getEmail());
        return ResponseEntity.ok(response);
    }
}  
