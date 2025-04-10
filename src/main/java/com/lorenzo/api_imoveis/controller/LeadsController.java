package com.lorenzo.api_imoveis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lorenzo.api_imoveis.entity.Leads;
import com.lorenzo.api_imoveis.services.LeadsService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/Leads")
public class LeadsController {
    @Autowired
    private LeadsService services;

    @ResponseBody
    @RequestMapping("/list")
    public List<Leads> list(){
        return services.getLeadsFromLibrary();
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path= "/register", method = RequestMethod.POST)
    public Leads register(@RequestBody Leads leads){
        return services.registerLeads(leads);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Leads photos(@PathVariable Long id, @RequestBody Leads leads){
        return services.updateLeads(id, leads);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        services.deleteLeads(id);
    }


}
