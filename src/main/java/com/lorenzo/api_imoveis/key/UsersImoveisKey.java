package com.lorenzo.api_imoveis.key;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersImoveisKey implements Serializable {

    @Column(name = "user_id")
    Long userId;


    @Column(name = "imovel_id")
    Long imovelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersImoveisKey)) return false;
        UsersImoveisKey that = (UsersImoveisKey) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(imovelId, that.imovelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, imovelId);
    }
}
