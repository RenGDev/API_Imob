package com.lorenzo.api_imoveis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzo.api_imoveis.DTOs.LoginRequestDTO;
import com.lorenzo.api_imoveis.DTOs.LoginResponseDTO;
import com.lorenzo.api_imoveis.exceptions.InvalidCredentialsException;
import com.lorenzo.api_imoveis.security.JwtUtil;
import com.lorenzo.api_imoveis.services.LoginService;


@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    
    private final LoginService service;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, LoginService service) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) throws InvalidCredentialsException {

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );
            
            String token = jwtUtil.generateToken(request.getEmail());
            return service.login(request, token);
            
        
    }
}