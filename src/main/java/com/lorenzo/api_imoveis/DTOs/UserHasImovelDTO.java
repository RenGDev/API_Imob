package com.lorenzo.api_imoveis.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHasImovelDTO {
    private Long userId;
    private Long imovelId;
} 
    