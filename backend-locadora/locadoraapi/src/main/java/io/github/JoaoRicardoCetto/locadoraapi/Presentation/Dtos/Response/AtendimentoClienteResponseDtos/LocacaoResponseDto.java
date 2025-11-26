package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos;

import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Cliente;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ItemResponseDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record LocacaoResponseDto (
        UUID id,
        LocalDate dtLocacao,
        LocalDate dtDevolucaoPrevista,
        LocalDate dtDevolucaoEfetiva,
        double valorCobrado,
        double multaCobrada,
        ItemResponseDto item,
        ClienteResponseDto cliente
){
}
