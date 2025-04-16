package com.lorenzo.api_imoveis.repository;

import com.lorenzo.api_imoveis.entity.Imoveis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImoveisRepository extends JpaRepository<Imoveis, Long> {

    List<Imoveis> findByTypeContainingIgnoreCase(String type);

}
