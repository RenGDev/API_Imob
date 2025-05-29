package com.lorenzo.api_imoveis.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 11, min = 11)
    private String cpf;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;


}