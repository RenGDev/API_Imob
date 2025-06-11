package com.lorenzo.api_imoveis.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.Photos;
import com.lorenzo.api_imoveis.repository.ImoveisRepository;
import com.lorenzo.api_imoveis.repository.PhotosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PhotosService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private final PhotosRepository repository;

    @Autowired
    private ImoveisRepository imoveisRepository;

    public PhotosService(PhotosRepository repository) {
        this.repository = repository;
    }


    public List<Photos> getPhotosFromLibrary(){
        return repository.findAll();
    }

    public Photos uploadPhoto(Long imovelId, MultipartFile file, String description, Boolean isPrimary) throws IOException {
        // Upload pro Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = uploadResult.get("secure_url").toString();

        // Busca o imóvel
        Imoveis imovel = imoveisRepository.findById(imovelId)
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado com ID: " + imovelId));

        // Salva no banco
        Photos photo = new Photos();
        photo.setUrl(imageUrl);
        photo.setDescription(description);
        photo.setImovel(imovel);
        photo.setIsPrimary(isPrimary);

        return repository.save(photo);
    }

    public void deletePhoto(Long id){
        repository.deleteById(id);
    }

    public Photos updatePhoto(Long id, Photos photo){
        photo.setId(id);
        return repository.save(photo);
    }
}
