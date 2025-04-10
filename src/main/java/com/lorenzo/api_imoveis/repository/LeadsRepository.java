package com.lorenzo.api_imoveis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorenzo.api_imoveis.entity.Leads;

public interface LeadsRepository extends JpaRepository<Leads, Long> {
    
}
