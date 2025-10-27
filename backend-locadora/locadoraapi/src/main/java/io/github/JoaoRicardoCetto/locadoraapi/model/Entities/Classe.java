package io.github.JoaoRicardoCetto.locadoraapi.model.Entities;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Classe extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(precision = 10)
    private double valor;

    @Column(nullable = false)
    private LocalDate prazoDevolucao;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    private List<Titulo> titulos;


    @Deprecated
    public Classe() {
    }

    public Classe(String nome, double valor, LocalDate prazoDevolucao, List<Titulo> titulos) {
        this.nome = nome;
        this.valor = valor;
        this.prazoDevolucao = prazoDevolucao;
        this.titulos = titulos;
    }
}
