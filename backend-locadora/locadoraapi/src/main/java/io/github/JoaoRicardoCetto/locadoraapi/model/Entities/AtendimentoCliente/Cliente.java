package io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public class Cliente extends BaseEntity {
    private int numInscricao;
    private String nome;
    private LocalDate dataNascimento;
    private char sexo;
    private Boolean estahAtivo;

    @Deprecated
    public Cliente() {
    }

    public Cliente(int numInscricao, String nome, LocalDate dataNascimento, char sexo, Boolean estahAtivo) {
        this.numInscricao = numInscricao;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.estahAtivo = estahAtivo;
    }
}


