package io.github.JoaoRicardoCetto.locadoraapi.model.Entities;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Ator extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToMany(mappedBy = "atores", fetch = FetchType.LAZY)
    private Set<Titulo> titulos = new HashSet<>();

    @Deprecated
    public Ator() {
    }

    public Ator(String nome, Set<Titulo> titulos) {
        this.nome = nome;
        this.titulos = titulos;
    }
}
