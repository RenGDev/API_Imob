package com.lorenzo.api_imoveis.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Getter
@Setter                                                                             
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
@JsonIgnoreProperties({"userHasImovel"})
public class Imoveis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /*@ManyToMany(mappedBy="imoveis")
    private List<Users> users; */
    

    private String description;
    private String address;
    private String type;
    private Double price;
    private Number bedRooms;
    private Number bathRooms;
    private Number size;

    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Photos> photos;

    @OneToMany(mappedBy = "imoveis", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserHasImovel> userhasimovel;
}
