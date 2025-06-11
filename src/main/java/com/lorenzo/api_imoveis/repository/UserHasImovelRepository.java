package com.lorenzo.api_imoveis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorenzo.api_imoveis.entity.UserHasImovel;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.key.UsersImoveisKey;

public interface UserHasImovelRepository extends JpaRepository<UserHasImovel, UsersImoveisKey> {

     List<UserHasImovel> findByUsers(Users user);

     List<UserHasImovel> findByUsersId(Long id);
 } 
    
