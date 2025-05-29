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
    private Double price;
    private Integer bedRooms;
    private Integer bathRooms;
    private Integer size;
}
