package com.lorenzo.api_imoveis.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImovelWithUsuariosDTO {
    private Long id;
    private String description;
    private String address;
    private Double price;
    private Number bedRooms;
    private Number bathRooms;
    private Number size;
    private List<UserDTO> usuarios;
}
