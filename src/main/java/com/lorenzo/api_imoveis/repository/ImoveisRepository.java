package com.lorenzo.api_imoveis.repository;

import com.lorenzo.api_imoveis.DTOs.DashboardItem;
import com.lorenzo.api_imoveis.entity.Imoveis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImoveisRepository extends JpaRepository<Imoveis, Long> {

    List<Imoveis> findByTypeContainingIgnoreCase(String type);
    
    @Query("SELECT i.type AS nome, COUNT(i) AS num FROM Imoveis i GROUP BY i.type")
    List<DashboardItem> contarPorTipo();

}
