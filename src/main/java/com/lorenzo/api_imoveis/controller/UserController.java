package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.services.UsersServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lorenzo.api_imoveis.entity.Users;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UsersServices services;

    @ResponseBody
    @RequestMapping("/list")
    public List<Users> list(){

        return services.getUsersFromLibrary();
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path= "/register", method = RequestMethod.POST)
    public Users register(@RequestBody Users user){
        return services.registerUser(user);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Users update(@PathVariable Long id, @RequestBody Users user){
        return services.updateUsers(id, user);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        services.deleteUsers(id);
    }

}