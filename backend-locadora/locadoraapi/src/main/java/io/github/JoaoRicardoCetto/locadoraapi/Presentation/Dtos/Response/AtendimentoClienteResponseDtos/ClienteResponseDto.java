package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;


public record ClienteResponseDto(
        UUID id,
        long numInscricao,
        String nome,
        LocalDate dataNascimento,
        char sexo,
        Boolean estahAtivo,
        List<LocacaoResponseDto> locacoes,
        String tipo
){
}
