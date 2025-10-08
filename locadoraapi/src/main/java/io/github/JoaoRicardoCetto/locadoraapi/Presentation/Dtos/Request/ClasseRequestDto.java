package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ClasseRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome,
        
        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser positivo")
        double valor,
        
        @NotNull(message = "Prazo de devolução é obrigatório")
        LocalDate prazoDevolucao
) {
}
