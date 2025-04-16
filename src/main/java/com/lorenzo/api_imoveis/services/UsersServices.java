package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.DTOs.ImovelDTO;
import com.lorenzo.api_imoveis.DTOs.UserWithImoveisDTO;
import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.UserHasImovel;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.repository.UserHasImovelRepository;
import com.lorenzo.api_imoveis.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServices {

     @Autowired
     private final UsersRepository repository;

     @Autowired
     private UserHasImovelRepository userHasImovelRepository;

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
                ImovelDTO imovelDTO = new ImovelDTO(imovel.getId(), imovel.getDescription(), imovel.getAddress(), imovel.getType(), null, imovel.getPrice(), null, null, null);
                imoveis.add(imovelDTO);
            }

            UserWithImoveisDTO userDTO = new UserWithImoveisDTO(user.getId(), user.getName(), user.getCpf(), user.getPhone(), user.getEmail(), imoveis);
            result.add(userDTO);
        }

        return result;
    }

    public Users registerUser(Users user){
         return repository.save(user);
    }

    public void deleteUsers(Long id){
         repository.deleteById(id);
    }

    public Users updateUsers(Long id, Users user){
        user.setId(id);
        return repository.save(user);
    }
}
