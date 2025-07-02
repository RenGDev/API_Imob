package com.lorenzo.api_imoveis.services;

import com.lorenzo.api_imoveis.DTOs.ImovelWithUsuariosDTO;
import com.lorenzo.api_imoveis.DTOs.UserDTO;
import com.lorenzo.api_imoveis.entity.Imoveis;
import com.lorenzo.api_imoveis.entity.Users;
import com.lorenzo.api_imoveis.repository.ImoveisRepository;
import com.lorenzo.api_imoveis.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImoveisService {


    @Autowired
    private final ImoveisRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    public ImoveisService(ImoveisRepository repository) {
        this.repository = repository;
    }

    public List<Imoveis> pesqByImoveis(String type){
        return repository.findByTypeContainingIgnoreCase(type);
    }

    public List<Imoveis> listImoveis(){
        return repository.findAll();
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
            imovel.getBathRooms(),
            imovel.getBedRooms(),
            imovel.getSize(),
            usuarios
        );
    }).collect(Collectors.toList());
}


    public ResponseEntity<Optional<Imoveis>> getImovelById(Long id){
        Optional<Imoveis> imovel = repository.findById(id);
        return ResponseEntity.ok(imovel);
    }

    public Imoveis registerImovel(Imoveis imovel){
         String email = ((UserDetails) SecurityContextHolder.getContext()
                          .getAuthentication().getPrincipal())
                          .getUsername();

        Users user = usersRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        imovel.setUser(user);

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
