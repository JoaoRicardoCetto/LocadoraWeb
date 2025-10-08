package io.github.JoaoRicardoCetto.locadoraapi.model.Entities;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Item extends BaseEntity {
    private int numSerie;

    private LocalDate dtAquisicao;

    @Column(length = 100)
    private String tipoItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Titulo titulo;

    @Deprecated
    public Item() {
    }

    public Item(int numSerie, LocalDate dtAquisicao, String tipoItem, Titulo titulo) {
        this.numSerie = numSerie;
        this.dtAquisicao = dtAquisicao;
        this.tipoItem = tipoItem;
        this.titulo = titulo;
    }
}
