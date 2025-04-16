package com.lorenzo.api_imoveis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lorenzo.api_imoveis.key.UsersImoveisKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



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
public class UserHasImovel {

    @EmbeddedId
    UsersImoveisKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    Users users;

    @ManyToOne
    @MapsId("imovelId")
    @JoinColumn(name = "imovel_id")
    @JsonManagedReference
    Imoveis imoveis;
}
