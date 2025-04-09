package com.lorenzo.api_imoveis.repository;

import com.lorenzo.api_imoveis.entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photos, Long> {

}
