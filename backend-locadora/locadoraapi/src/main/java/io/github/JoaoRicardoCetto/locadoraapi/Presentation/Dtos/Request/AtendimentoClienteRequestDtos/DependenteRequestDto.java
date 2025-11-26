package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DependenteRequestDto (
        @NotBlank(message = "O nome do dependente é obrigatório")
        String nome,
        LocalDate dtNascimento,
        char sexo,
        Boolean estahAtivo,
        @NotNull(message = "O sócio é obrigatório")
        UUID socioId
) {
}
