package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.AtendimentoClienteRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DependenteRequestDto (
        @NotNull(message = "O número de inscrição é obrigatório")
        String numInscricao,
        @NotBlank(message = "O nome do dependente é obrigatório")
        String nome,
        LocalDate dtNascimento,
        char sexo,
        Boolean estahAtivo,
        @NotNull(message = "O sócio é obrigatório")
        UUID socioId
) {
}
