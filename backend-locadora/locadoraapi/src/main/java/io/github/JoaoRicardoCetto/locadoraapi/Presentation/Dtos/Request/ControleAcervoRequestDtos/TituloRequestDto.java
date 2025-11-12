package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ControleAcervoRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record TituloRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome,
        
        @NotNull(message = "Ano é obrigatório")
        LocalDate ano,
        
        @Size(max = 500, message = "Sinopse deve ter no máximo 500 caracteres")
        String sinopse,
        
        @Size(max = 100, message = "Categoria deve ter no máximo 100 caracteres")
        String categoria,
        
        UUID diretorId,
        UUID classeId,
        Set<UUID> atoresIds,
        List<UUID> itensIds
) {
}
