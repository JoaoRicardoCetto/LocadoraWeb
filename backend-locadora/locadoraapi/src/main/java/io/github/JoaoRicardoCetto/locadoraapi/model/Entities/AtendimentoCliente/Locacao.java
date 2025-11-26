package io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente;

import io.github.JoaoRicardoCetto.locadoraapi.model.common.BaseEntity;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Locacao extends BaseEntity {
    private LocalDate dtLocacao;
    private LocalDate dtDevolucaoPrevista;
    private LocalDate dtDevolucaoEfetiva;
    private double valorCobrado;
    private double multaCobrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Deprecated
    public Locacao() {
    }

    public Locacao(LocalDate dtLocacao, LocalDate dtDevolucaoPrevista, LocalDate dtDevolucaoEfetiva, double valorCobrado, double multaCobrada, Item item, Cliente cliente) {
        this.dtLocacao = dtLocacao;
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
        this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
        this.valorCobrado = valorCobrado;
        this.multaCobrada = multaCobrada;
        this.item = item;
        this.cliente = cliente;
    }
}
