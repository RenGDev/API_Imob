package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServices {

     @Autowired
     private final UsersRepository repository;

    public UsersServices(UsersRepository repository) {
        this.repository = repository;
    }

    public List<Users> getUsersFromLibrary(){
         return repository.findAll();
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
