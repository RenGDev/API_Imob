package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.entity.Photos;
import com.lorenzo.api_imoveis.services.PhotosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
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
    @RequestMapping(path= "/upload/{imovelId}", method = RequestMethod.POST)
    public Photos uploadPhoto(
            @PathVariable Long imovelId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "isPrimary", required = false) Boolean isPrimary
    ) throws IOException {
        return services.uploadPhoto(imovelId, file, description, isPrimary);
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
