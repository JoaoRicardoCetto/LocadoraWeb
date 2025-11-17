package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos;

import java.util.List;
import java.util.UUID;

public record ClasseResponseDto(
        UUID id,
        String nome,
        double valor,
        int prazoDevolucao,
        List<TituloResponseDto> titulos
) {
}
