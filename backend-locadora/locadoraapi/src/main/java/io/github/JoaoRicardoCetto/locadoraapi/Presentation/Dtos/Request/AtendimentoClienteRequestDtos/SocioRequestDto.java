package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.AtendimentoClienteRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record SocioRequestDto (
        @NotNull(message = "O número de inscrição é obrigatório")
        String numInscricao,
        @NotBlank(message = "O nome do dependente é obrigatório")
        String nome,
        LocalDate dtNascimento,
        char sexo,
        Boolean estahAtivo,
        @NotBlank
        @CPF
        String cpf,
        String endereco,
        String telefone
) {
}
