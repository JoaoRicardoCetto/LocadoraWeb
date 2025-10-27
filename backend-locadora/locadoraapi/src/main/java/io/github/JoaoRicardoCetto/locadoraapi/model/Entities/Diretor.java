package io.github.JoaoRicardoCetto.locadoraapi.model.Entities;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Diretor extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "diretor", fetch = FetchType.LAZY)
    private List<Titulo> titulos;

    @Deprecated
    public Diretor() {
    }

    public Diretor(String nome, List<Titulo> titulos) {
        this.nome = nome;
        this.titulos = titulos;
    }
}
