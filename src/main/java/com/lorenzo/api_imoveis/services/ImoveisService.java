package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.DTOs.ImovelWithUsuariosDTO;
import com.lorenzo.api_imoveis.DTOs.UserDTO;
import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.repository.ImoveisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImoveisService {


    @Autowired
    private final ImoveisRepository repository;

    public ImoveisService(ImoveisRepository repository) {
        this.repository = repository;
    }

    public List<Imoveis> pesqByImoveis(String type){
        return repository.findByTypeContainingIgnoreCase(type);
    }


    public List<ImovelWithUsuariosDTO> getImoveisComUsuarios() {
    List<Imoveis> imoveis = repository.findAll();

    return imoveis.stream().map(imovel -> {
        List<UserDTO> usuarios = imovel.getUserhasimovel()
            .stream()
            .map(link -> {
                Users user = link.getUsers();
                return new UserDTO(user.getId(), user.getName(), user.getCpf(), user.getPhone(), user.getEmail());
            })
            .collect(Collectors.toList());

        return new ImovelWithUsuariosDTO(
            imovel.getId(),
            imovel.getDescription(),
            imovel.getAddress(),
            imovel.getPrice(),
            imovel.getPhoto(),
            imovel.getBathRooms(),
            imovel.getBedRooms(),
            imovel.getSize(),
            usuarios
        );
    }).collect(Collectors.toList());
}


    public Optional<Imoveis> getImovelById(Long id){
        return repository.findById(id);
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
