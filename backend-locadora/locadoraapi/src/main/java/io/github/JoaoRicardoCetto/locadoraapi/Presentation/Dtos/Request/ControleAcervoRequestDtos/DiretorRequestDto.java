package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DiretorRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome
) {
}
