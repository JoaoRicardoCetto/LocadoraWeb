package io.github.JoaoRicardoCetto.locadoraapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
public class Titulo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    private LocalDate ano;

    private String sinopse;

    @Column(length = 100)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "id_diretor")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe classe;

    @OneToMany(mappedBy = "titulo")
    private List<Item> itens;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "titulo_ator",
            joinColumns = @JoinColumn(name = "id_titulo"),
            inverseJoinColumns = @JoinColumn(name = "id_ator"))
    private Set<Ator> atores = new HashSet<>();

    @Deprecated
    public Titulo() {
    }

    public Titulo(String nome, LocalDate ano, String sinopse, String categoria) {
        this.nome = nome;
        this.ano = ano;
        this.sinopse = sinopse;
        this.categoria = categoria;
    }
}

