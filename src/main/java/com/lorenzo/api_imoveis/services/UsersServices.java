package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.DTOs.ImovelDTO;
import com.lorenzo.api_imoveis.DTOs.UserWithImoveisDTO;
import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.UserHasImovel;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.repository.UserHasImovelRepository;
import com.lorenzo.api_imoveis.repository.UsersRepository;
import com.lorenzo.api_imoveis.utils.EmailService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServices {

     @Autowired
     private final UsersRepository repository;

     @Autowired
     private UserHasImovelRepository userHasImovelRepository;
     
     @Autowired
     private EmailService emailService;

    public UsersServices(UsersRepository repository) {
        this.repository = repository;
    }

    public List<UserWithImoveisDTO> getUsersFromLibrary(){
         List<UserWithImoveisDTO> result = new ArrayList<>();

        List<Users> usersList = repository.findAll();

        for (Users user : usersList) {
            List<UserHasImovel> userHasImovelList = userHasImovelRepository.findByUsers(user); // Aqui você pega todos os UserHasImovel para um usuário específico

            List<ImovelDTO> imoveis = new ArrayList<>();
            for (UserHasImovel userHasImovel : userHasImovelList) {
                Imoveis imovel = userHasImovel.getImoveis();
                ImovelDTO imovelDTO = new ImovelDTO(imovel.getId(), imovel.getDescription(), imovel.getAddress(), imovel.getType(), imovel.getPriceType(), imovel.getPrice(), null, null, null, null);
                imoveis.add(imovelDTO);
            }

            UserWithImoveisDTO userDTO = new UserWithImoveisDTO(user.getId(), user.getName(), user.getCpf(), user.getPhone(), user.getEmail(), imoveis);
            result.add(userDTO);
        }

        return result;
    }

    public Optional<Users> getUser(Long id){
        return repository.findById(id);
    }

    public Optional<Users> getUserByEmail(String email){
        return repository.findByEmail(email);
    }
    
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
    
    public ResponseEntity<?> registerUser(Users user) {
        // A senha já deve vir hasheada do controller
        Users response = repository.save(user);
        return ResponseEntity.ok(response);
    }

    public void deleteUsers(Long id){
         repository.deleteById(id);
    }

    public ResponseEntity<?> sendPasswordRecoveryCode(String email) {
        Optional<Users> optionalUser = repository.findByEmail(email);
        
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário com este e-mail não encontrado.");
        }

        Users user = optionalUser.get();

        // Gerar código aleatório
        String codigo = String.valueOf((int) (Math.random() * 900_000) + 100_000); // 6 dígitos

        try {
            emailService.sendPasswordRecovery(user.getName(), user.getEmail(), codigo);
        } catch (MessagingException e) {
            return ResponseEntity.internalServerError().body("Erro ao enviar e-mail: " + e.getMessage());
        }

        user.setCode(codigo);
        repository.save(user);

        return ResponseEntity.ok("Código de recuperação enviado para o e-mail.");
    }

    public Users updateUsers(Long id, Users user){
        user.setId(id);
        return repository.save(user);
    }

    public ResponseEntity<?> resetPassword(String email, String codigo, String novaSenha) {
        Optional<Users> optionalUser = repository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário com este e-mail não foi encontrado.");
        }

        Users user = optionalUser.get();

        if (user.getCode() == null || !user.getCode().equals(codigo)) {
            return ResponseEntity.badRequest().body("Código de recuperação inválido.");
        }

        // Hash da nova senha
        String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);
        user.setPassword(senhaCriptografada);

        // Limpa o código de recuperação
        user.setCode(null);

        repository.save(user);

        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }
}
