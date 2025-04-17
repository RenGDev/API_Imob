package com.lorenzo.api_imoveis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorenzo.api_imoveis.entity.UserHasImovel;
import com.lorenzo.api_imoveis.entity.Users;

public interface UserHasImovelRepository extends JpaRepository<UserHasImovel, Long> {

     List<UserHasImovel> findByUsers(Users user);
} 
    
