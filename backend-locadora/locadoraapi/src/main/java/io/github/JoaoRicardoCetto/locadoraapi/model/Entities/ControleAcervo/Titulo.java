package io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Titulo extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String nome;

    private LocalDate ano;

    private String sinopse;

    @Column(length = 100)
    private String categoria;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_diretor")
    private Diretor diretor;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_classe")
    private Classe classe;

    @OneToMany(mappedBy = "titulo", fetch = FetchType.LAZY)
    private List<Item> itens = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "titulo_ator",
            joinColumns = @JoinColumn(name = "id_titulo"),
            inverseJoinColumns = @JoinColumn(name = "id_ator"))
    private Set<Ator> atores = new HashSet<>();

    @Deprecated
    public Titulo() {
    }

    public Titulo(String nome, LocalDate ano, String sinopse, String categoria, Diretor diretor, Classe classe, Set<Ator> atores, List<Item> itens) {
        this.nome = nome;
        this.ano = ano;
        this.sinopse = sinopse;
        this.categoria = categoria;
        this.diretor = diretor;
        this.classe = classe;
        this.atores = atores;
        this.itens = itens;
        
    }
}

