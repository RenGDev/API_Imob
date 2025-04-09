package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.entity.Corretores;
import com.lorenzo.api_imoveis.services.CorretoresService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/corretores")
public class CorretoresController {

    @Autowired
    private CorretoresService services;

    @ResponseBody
    @RequestMapping("/list")
    public List<Corretores> list(){
        return services.getUsersFromLibrary();
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path= "/register", method = RequestMethod.POST)
    public Corretores register(@RequestBody Corretores corretores){
        return services.registerCorretor(corretores);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Corretores update(@PathVariable Long id, @RequestBody Corretores corretor){
        return services.updateCorretor(id, corretor);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        services.deleteCorretor(id);
    }
}
