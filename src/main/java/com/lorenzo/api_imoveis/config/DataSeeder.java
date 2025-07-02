package com.lorenzo.api_imoveis.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.entity.enums.Role;
import com.lorenzo.api_imoveis.repository.UsersRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initAdmin(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("admin@email.com")) {
                Users admin = new Users();
                admin.setName("Administrador");
                admin.setEmail("admin@email.com");
                admin.setPassword(passwordEncoder.encode("vaitomanocugremio")); 
                admin.setRole(Role.ADMIN); 
                userRepository.save(admin);
                System.out.println("Admin criado com sucesso.");
            } else {
                System.out.println("Admin já existe.");
            }
        };
    }
}