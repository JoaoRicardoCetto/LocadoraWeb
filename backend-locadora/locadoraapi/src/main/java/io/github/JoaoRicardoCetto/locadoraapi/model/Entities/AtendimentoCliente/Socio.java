package io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Socio extends Cliente{
    private String cpf;
    private String endereço;
    private String telefone;

    @OneToMany(mappedBy = "socio", fetch = FetchType.LAZY)
    private List<Dependente> dependentes = new ArrayList<>();

    @Deprecated
    public Socio() {
    }

    public Socio(int numInscricao, String nome, LocalDate dataNascimento, char sexo, Boolean estahAtivo, String cpf, String endereço, String telefone, List<Dependente> dependentes) {
        super(numInscricao, nome, dataNascimento, sexo, estahAtivo);
        this.cpf = cpf;
        this.endereço = endereço;
        this.telefone = telefone;
        this.dependentes = dependentes;
    }
}
