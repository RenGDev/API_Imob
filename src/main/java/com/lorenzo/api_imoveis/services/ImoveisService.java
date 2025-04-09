package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.repository.ImoveisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImoveisService {


    @Autowired
    private final ImoveisRepository repository;

    public ImoveisService(ImoveisRepository repository) {
        this.repository = repository;
    }


    public List<Imoveis> getImoveisFromLibrary(){
        return repository.findAll();
    }

    public Imoveis registerImovel(Imoveis imovel){
        return repository.save(imovel);
    }

    public void deleteImovel(Long id){
        repository.deleteById(id);
    }

    public Imoveis updateImovel(Long id, Imoveis imovel){
        imovel.setId(id);
        return repository.save(imovel);
    }

}
