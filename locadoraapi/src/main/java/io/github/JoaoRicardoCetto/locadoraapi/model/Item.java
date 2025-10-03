package io.github.JoaoRicardoCetto.locadoraapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Data
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private int numSerie;

    private LocalDate dtAquisicao;

    @Column(length = 100)
    private String tipoItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Titulo titulo;

    @Deprecated
    public Item() {
    }

    public Item(int numSerie, LocalDate dtAquisicao, String tipoItem) {
        this.numSerie = numSerie;
        this.dtAquisicao = dtAquisicao;
        this.tipoItem = tipoItem;
    }
}
