package com.lorenzo.api_imoveis.repository;

import com.lorenzo.api_imoveis.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByImovelId(Long imovelId);
    boolean existsByUserIdAndImovelId(Long userId, Long imovelId);
}
