package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record SocioResponseDto (
        UUID id,
        String numInscricao,
        String nome,
        LocalDate dtNascimento,
        char sexo,
        Boolean estahAtivo,
        String cpf,
        String endereco,
        String telefone,
        List<DependenteResponseDto> dependentes

){
}
