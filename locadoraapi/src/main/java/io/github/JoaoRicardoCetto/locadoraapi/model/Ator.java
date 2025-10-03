package io.github.JoaoRicardoCetto.locadoraapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
public class Ator {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToMany(mappedBy = "atores", fetch = FetchType.LAZY)
    private Set<Titulo> titulos = new HashSet<>();

    @Deprecated
    public Ator() {
    }

    public Ator(String nome) {
        this.nome = nome;
    }
}
