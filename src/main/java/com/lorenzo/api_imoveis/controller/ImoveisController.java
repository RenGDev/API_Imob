package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.DTOs.ImovelWithUsuariosDTO;
import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.services.ImoveisService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("imoveis")
public class ImoveisController {

    @Autowired
    private ImoveisService services;

    @ResponseBody
    @RequestMapping("/list")
    public List<ImovelWithUsuariosDTO> listarImoveisComUsuarios() {
        return services.getImoveisComUsuarios();
    }

    @ResponseBody
    @RequestMapping("/list/{id}")
    public Optional<Imoveis> getImovel(@PathVariable Long id){
        return services.getImovelById(id);
    }

    
    @ResponseBody
    @RequestMapping("/pesq")
    public ResponseEntity<List<Imoveis>> pesq(@RequestParam String type){
        if (type != null) {
            return ResponseEntity.ok(services.pesqByImoveis(type));
        }

        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PostMapping(path= "/register")
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
