package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.entity.Photos;
import com.lorenzo.api_imoveis.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotosService {

    @Autowired
    private final PhotosRepository repository;

    public PhotosService(PhotosRepository repository) {
        this.repository = repository;
    }


    public List<Photos> getPhotosFromLibrary(){
        return repository.findAll();
    }

    public Photos registerPhoto(Photos photos){
        return repository.save(photos);
    }

    public void deletePhoto(Long id){
        repository.deleteById(id);
    }

    public Photos updatePhoto(Long id, Photos photo){
        photo.setId(id);
        return repository.save(photo);
    }
}
