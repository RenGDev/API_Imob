package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.entity.Corretores;
import com.lorenzo.api_imoveis.repository.CorretoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorretoresService {

    @Autowired
    private final CorretoresRepository repository;

    public CorretoresService(CorretoresRepository repository) {
        this.repository = repository;
    }

    public List<Corretores> getUsersFromLibrary(){
        return repository.findAll();
    }

    public Corretores registerCorretor(Corretores corretor){
        return repository.save(corretor);
    }

    public void deleteCorretor(Long id){
        repository.deleteById(id);
    }

    public Corretores updateCorretor(Long id, Corretores corretor){
        corretor.setId(id);
        return repository.save(corretor);
    }

}
