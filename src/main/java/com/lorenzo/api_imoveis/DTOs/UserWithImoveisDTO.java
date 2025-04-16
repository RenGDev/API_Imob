package com.lorenzo.api_imoveis.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithImoveisDTO {
    private Long userId;
    private String userName;
    private String cpf;
    private String phone;
    private String email;
    private List<ImovelDTO> imoveis;
}
