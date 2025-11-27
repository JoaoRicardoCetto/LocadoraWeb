package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos;

import java.time.LocalDate;

public record LocacaoDevolvidaRequestDto(
        LocalDate dtDevolucaoEfetiva
) {
}
