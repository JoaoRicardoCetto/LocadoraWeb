package io.github.JoaoRicardoCetto.locadoraapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Classe {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(precision = 10)
    private double valor;

    @Column(nullable = false)
    private LocalDate prazoDevolucao;

    @OneToMany(mappedBy = "classe")
    private List<Titulo> titulos;


    @Deprecated
    public Classe() {
    }

    public Classe(String nome, double valor, LocalDate prazoDevolucao) {
        this.nome = nome;
        this.valor = valor;
        this.prazoDevolucao = prazoDevolucao;
    }
}
