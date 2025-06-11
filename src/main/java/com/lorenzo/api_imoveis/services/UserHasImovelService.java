package com.lorenzo.api_imoveis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.UserHasImovel;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.key.UsersImoveisKey;
import com.lorenzo.api_imoveis.repository.ImoveisRepository;
import com.lorenzo.api_imoveis.repository.UserHasImovelRepository;
import com.lorenzo.api_imoveis.repository.UsersRepository;
import com.lorenzo.api_imoveis.utils.EmailService;

@Service
public class UserHasImovelService {
    
    @Autowired
    private final UserHasImovelRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ImoveisRepository imoveisRepository;

    @Autowired
    private EmailService emailService;
    
    public UserHasImovelService(UserHasImovelRepository repository) {
        this.repository = repository;
    }

    public List<UserHasImovel> getUserHasImovelFromLibrary(){
        return repository.findAll();
    }

    public ResponseEntity<List<UserHasImovel>> getProposeByUser(Long userId){
        List<UserHasImovel> response = repository.findByUsersId(userId);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<UserHasImovel> registerUserHasImovel(Long userId, Long imovelId){
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        Imoveis imovel = imoveisRepository.findById(imovelId)
            .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        UsersImoveisKey id = new UsersImoveisKey();
        id.setUserId(userId);
        id.setImovelId(imovelId);

    
        UserHasImovel entity = new UserHasImovel();
        entity.setId(id);
        entity.setUsers(user);
        entity.setImoveis(imovel);

        UserHasImovel response = repository.save(entity);

        try {
            String nome = user.getName(); 
            String email = user.getEmail();
            String descricao = "Proposta de interesse no imóvel: " + imovel.getDescription(); 
            String resposta = "Recebemos sua proposta! Em breve entraremos em contato.";

            emailService.enviarEmailHtml(nome, email, descricao, resposta);
        } catch (Exception e) {
          
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    public void deleteUserHasImovel(Long userId, Long imovelId){
        UsersImoveisKey id = new UsersImoveisKey();
        id.setUserId(userId);
        id.setImovelId(imovelId);

        repository.deleteById(id);
    }
}
