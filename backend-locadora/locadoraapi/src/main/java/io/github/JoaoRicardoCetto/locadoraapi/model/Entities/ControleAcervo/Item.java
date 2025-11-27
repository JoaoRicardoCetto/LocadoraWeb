package io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo;

import io.github.JoaoRicardoCetto.locadoraapi.model.common.BaseEntity;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Item extends BaseEntity {
    @Column(nullable = false, unique = true)
    private int numSerie;

    private LocalDate dtAquisicao;

    @Column(length = 100)
    private String tipoItem;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_titulo")
    private Titulo titulo;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Locacao> locacoes = new ArrayList<>();

    @Deprecated
    public Item() {
    }

    public Item(int numSerie, LocalDate dtAquisicao, String tipoItem, Titulo titulo, List<Locacao> locacoes) {
        this.numSerie = numSerie;
        this.dtAquisicao = dtAquisicao;
        this.tipoItem = tipoItem;
        this.titulo = titulo;
        this.locacoes = locacoes;
    }
}
