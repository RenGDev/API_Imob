package com.lorenzo.api_imoveis.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Imoveis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy="imoveis")
    private List<Users> users;

    private String description;
    private String address;
    private int price;
    private Number bedRooms;
    private Number bathRooms;
    private Number size;

    @ManyToOne
    private Corretores corretores;
}
