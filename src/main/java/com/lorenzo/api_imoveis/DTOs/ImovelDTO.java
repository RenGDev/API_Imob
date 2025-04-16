package com.lorenzo.api_imoveis.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImovelDTO {
    private Long id;
    private String description;
    private String address;
    private String type;
    private String photo;
    private Double price;
    private Number bedRooms;
    private Number bathRooms;
    private Number size;
}
