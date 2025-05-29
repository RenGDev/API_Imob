package com.lorenzo.api_imoveis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzo.api_imoveis.DTOs.UserHasImovelDTO;
import com.lorenzo.api_imoveis.entity.UserHasImovel;
import com.lorenzo.api_imoveis.services.UserHasImovelService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("user_imovel")
public class UserHasImovelController {

    @Autowired
    private UserHasImovelService services;

    @ResponseBody
    @RequestMapping("/list")
    public List<UserHasImovel> getUserHasImovelFromLibrary(){
        return services.getUserHasImovelFromLibrary();
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path= "/register", method = RequestMethod.POST)
    public UserHasImovel registerUserHasImovel(@RequestBody UserHasImovelDTO dto){
        return services.registerUserHasImovel(dto.getUserId(), dto.getImovelId());
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserHasImovel(@PathVariable Long id){
        services.deleteUserHasImovel(id);
    }
}

    
