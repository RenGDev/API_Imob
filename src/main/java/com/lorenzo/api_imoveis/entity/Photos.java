package com.lorenzo.api_imoveis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private String description;

    @ManyToOne
    @JsonIgnoreProperties("photos")
    private Imoveis imovel;
}
