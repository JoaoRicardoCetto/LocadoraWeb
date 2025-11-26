package io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente;

import io.github.JoaoRicardoCetto.locadoraapi.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Cliente extends BaseEntity {
    private long numInscricao;
    private String nome;
    private LocalDate dataNascimento;
    private char sexo;
    private Boolean estahAtivo;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Locacao> locacoes = new ArrayList<>();

    @Deprecated
    public Cliente() {
    }

    public Cliente(String nome, LocalDate dataNascimento, char sexo, Boolean estahAtivo, List<Locacao> locacoes) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.estahAtivo = true;
        this.locacoes = locacoes;
    }
}


