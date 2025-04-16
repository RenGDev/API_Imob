package com.lorenzo.api_imoveis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.UserHasImovel;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.key.UsersImoveisKey;
import com.lorenzo.api_imoveis.repository.ImoveisRepository;
import com.lorenzo.api_imoveis.repository.UserHasImovelRepository;
import com.lorenzo.api_imoveis.repository.UsersRepository;

@Service
public class UserHasImovelService {
    
    @Autowired
    private final UserHasImovelRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ImoveisRepository imoveisRepository;

    
    public UserHasImovelService(UserHasImovelRepository repository) {
        this.repository = repository;
    }

    public List<UserHasImovel> getUserHasImovelFromLibrary(){
        return repository.findAll();
    }

    public UserHasImovel registerUserHasImovel(Long userId, Long imovelId){
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Imoveis imovel = imoveisRepository.findById(imovelId).orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
        
        // 👉 Criar a chave composta
        UsersImoveisKey id = new UsersImoveisKey();
        id.setUserId(userId);
        id.setImovelId(imovelId);
        
        // 👉 Criar entidade e setar tudo manualmente
        UserHasImovel entity = new UserHasImovel();
        entity.setId(id);
        entity.setUsers(user);
        entity.setImoveis(imovel);
        
        return repository.save(entity);
    }

    public void deleteUserHasImovel(Long id){
        repository.deleteById(id);
    }
}
