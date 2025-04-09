package com.lorenzo.api_imoveis.repository;

import com.lorenzo.api_imoveis.entity.Imoveis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImoveisRepository extends JpaRepository<Imoveis, Long> {

}
