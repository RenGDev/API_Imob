package com.lorenzo.api_imoveis.DTOs;

import com.lorenzo.api_imoveis.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private Users user;
    private String token;
}