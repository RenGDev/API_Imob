package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.entity.Rating;
import com.lorenzo.api_imoveis.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepo;


    @PostMapping("/rate")
    public Rating rateImovel(@RequestBody Rating rating) {
        if (ratingRepo.existsByUserIdAndImovelId(rating.getUser().getId(), rating.getImovel().getId())) {
            throw new RuntimeException("Usuário já avaliou este imóvel");
        }
        return ratingRepo.save(rating);
    }

    @GetMapping("/imovel/{id}")
    public List<Rating> getRatingsByImovel(@PathVariable Long id) {
        return ratingRepo.findByImovelId(id);
    }

    @GetMapping("/media/{imovelId}")
    public Double getMediaByImovel(@PathVariable Long imovelId) {
    List<Rating> ratings = ratingRepo.findByImovelId(imovelId);
    if (ratings.isEmpty()) return 0.0;

    return ratings.stream()
            .mapToInt(Rating::getScore)
            .average()
            .orElse(0.0);
    }
}

