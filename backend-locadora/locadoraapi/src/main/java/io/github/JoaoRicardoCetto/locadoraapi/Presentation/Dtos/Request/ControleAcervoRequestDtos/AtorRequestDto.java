package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtorRequestDto(
        @NotBlank(message = "O campo nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome
) {
}
