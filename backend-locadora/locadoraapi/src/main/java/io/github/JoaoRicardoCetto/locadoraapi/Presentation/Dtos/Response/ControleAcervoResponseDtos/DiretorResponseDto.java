package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ControleAcervoResponseDtos;

import java.util.List;
import java.util.UUID;

public record DiretorResponseDto(
        UUID id,
        String nome,
        List<TituloResponseDto> titulos
) {
}
