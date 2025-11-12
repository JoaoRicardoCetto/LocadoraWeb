package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos;

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
