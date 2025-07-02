package com.lorenzo.api_imoveis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzo.api_imoveis.DTOs.DashboardItem;
import com.lorenzo.api_imoveis.repository.ImoveisRepository;
import com.lorenzo.api_imoveis.repository.UsersRepository;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ImoveisRepository imoveisRepository;

    @ResponseBody
    @GetMapping("/estatisticas")
    public ResponseEntity<?> getAll(){
        long totalUsers = usersRepository.count();
        long totalImoveis = imoveisRepository.count();

        Map<String, Object> json = new HashMap<>();
        json.put("totalUsuarios", totalUsers);
        json.put("totalImoveis", totalImoveis);
        
        return ResponseEntity.ok(json);
    }

    @GetMapping("/imoveisByType")
    public ResponseEntity<List<DashboardItem>> imoveisPorTipo() {
        List<DashboardItem> resultado = imoveisRepository.contarPorTipo();
        return ResponseEntity.ok(resultado);
    }
}
