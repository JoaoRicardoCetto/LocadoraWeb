package io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    public Dependente(String nome, LocalDate dataNascimento, char sexo, Boolean estahAtivo, List<Locacao> locacoes, Socio socio) {
        super(nome, dataNascimento, sexo, estahAtivo, locacoes);
        this.socio = socio;
    }
}
