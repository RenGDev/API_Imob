package com.lorenzo.api_imoveis.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequestDTO {
    private String email;
    private String codigo;
    private String novaSenha;
}
