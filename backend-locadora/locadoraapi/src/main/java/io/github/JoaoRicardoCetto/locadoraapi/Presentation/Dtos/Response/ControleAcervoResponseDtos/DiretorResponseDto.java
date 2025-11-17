package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos;

import java.util.List;
import java.util.UUID;

public record DiretorResponseDto(
        UUID id,
        String nome,
        List<TituloResponseDto> titulos
) {
}
