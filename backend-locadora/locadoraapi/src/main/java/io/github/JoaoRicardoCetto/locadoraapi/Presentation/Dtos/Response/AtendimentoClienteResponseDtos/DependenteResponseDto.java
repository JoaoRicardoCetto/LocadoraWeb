package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos;

import java.time.LocalDate;
import java.util.UUID;

public record DependenteResponseDto (
        UUID id,
        String numInscricao,
        String nome,
        LocalDate dtNascimento,
        char sexo,
        Boolean estahAtivo,
        SocioResponseDto socio
){
}
