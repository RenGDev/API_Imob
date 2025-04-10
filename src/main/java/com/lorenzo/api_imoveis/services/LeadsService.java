package com.lorenzo.api_imoveis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorenzo.api_imoveis.entity.Leads;
import com.lorenzo.api_imoveis.repository.LeadsRepository;

@Service
public class LeadsService {

    @Autowired
    private final LeadsRepository repository;
    
    public LeadsService(LeadsRepository repository){
        this.repository = repository;
    }

       public List<Leads> getLeadsFromLibrary(){
         return repository.findAll();
    }

    public Leads registerLeads(Leads leads){
         return repository.save(leads);
    }

    public void deleteLeads(Long id){
         repository.deleteById(id);
    }

    public Leads updateLeads(Long id, Leads leads){
        leads.setId(id);
        return repository.save(leads);
    }


}
