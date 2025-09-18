package io.github.JoaoRicardoCetto.locadoraapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Diretor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "diretor")
    private List<Titulo> titulos;

    @Deprecated
    public Diretor() {
    }

    public Diretor(String nome) {
        this.nome = nome;
    }
}
