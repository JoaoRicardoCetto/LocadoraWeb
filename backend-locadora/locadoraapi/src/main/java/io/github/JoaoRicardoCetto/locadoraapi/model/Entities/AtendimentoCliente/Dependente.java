package io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Dependente extends Cliente{

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @Deprecated
    public Dependente() {
    }

    public Dependente(int numInscricao, String nome, LocalDate dataNascimento, char sexo, Boolean estahAtivo, Socio socio) {
        super(numInscricao, nome, dataNascimento, sexo, estahAtivo);
        this.socio = socio;
    }
}
