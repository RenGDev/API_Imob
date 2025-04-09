package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.Photos;
import com.lorenzo.api_imoveis.services.ImoveisService;
import com.lorenzo.api_imoveis.services.PhotosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("photos")
public class PhotosController {

    @Autowired
    private PhotosService services;

    @ResponseBody
    @RequestMapping("/list")
    public List<Photos> list(){
        return services.getPhotosFromLibrary();
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path= "/register", method = RequestMethod.POST)
    public Photos register(@RequestBody Photos photos){
        return services.registerPhoto(photos);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Photos photos(@PathVariable Long id, @RequestBody Photos photo){
        return services.updatePhoto(id, photo);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        services.deletePhoto(id);
    }

}
