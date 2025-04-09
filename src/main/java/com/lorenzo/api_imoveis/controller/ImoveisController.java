package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.services.ImoveisService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/imoveis")
public class ImoveisController {

    @Autowired
    private ImoveisService services;

    @ResponseBody
    @RequestMapping("/list")
    public List<Imoveis> list(){
        return services.getImoveisFromLibrary();
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path= "/register", method = RequestMethod.POST)
    public Imoveis register(@RequestBody Imoveis imovel){
        return services.registerImovel(imovel);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Imoveis update(@PathVariable Long id, @RequestBody Imoveis imovel){
        return services.updateImovel(id, imovel);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        services.deleteImovel(id);
    }
}
