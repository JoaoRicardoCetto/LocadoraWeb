package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos;



import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record LocacaoRequestDto (
    LocalDate dtLocacao,
    LocalDate dtDevolucaoPrevista,
    LocalDate dtDevolucaoEfetiva,
    double valorCobrado,
    double multaCobrada,

    @NotNull(message = "O item a ser locado é obrigatório")
    UUID itemId,
    UUID clienteId,
    UUID socioId,
    UUID dependenteId
){
}
