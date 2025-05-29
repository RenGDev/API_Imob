package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.DTOs.RegisterRequestDTO;
import com.lorenzo.api_imoveis.DTOs.UserWithImoveisDTO;
import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.services.UsersServices;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersServices usersServices;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UsersServices usersServices, PasswordEncoder passwordEncoder) {
        this.usersServices = usersServices;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public List<UserWithImoveisDTO> list() {
        return usersServices.getUsersFromLibrary();
    }

    @ResponseBody
    @RequestMapping("/list/{id}")
    public Optional<Users> getUser(@PathVariable Long id){
        return usersServices.getUser(id);
    }

    @ResponseBody
    @RequestMapping("/list/{email}")
    public Optional<Users> getUserFromEmail(@PathVariable String email){
        return usersServices.getUserByEmail(email);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        try {
            if (usersServices.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.badRequest().body("Email já está em uso");
            }
            
            Users newUser = new Users();
            newUser.setName(registerRequest.getName());
            newUser.setCpf(registerRequest.getCpf());
            newUser.setPhone(registerRequest.getPhone());
            newUser.setEmail(registerRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            
            Users registeredUser = usersServices.registerUser(newUser);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Users update(@PathVariable Long id, @RequestBody Users user) {
        return usersServices.updateUsers(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usersServices.deleteUsers(id);
    }
}