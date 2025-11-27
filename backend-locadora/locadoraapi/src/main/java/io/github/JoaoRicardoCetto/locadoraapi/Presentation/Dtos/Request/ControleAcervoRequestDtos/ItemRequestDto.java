package io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ItemRequestDto(
        @NotBlank(message = "Tipo é obrigatório")
        @Size(min = 2, max = 100, message = "Tipo deve ter entre 2 e 100 caracteres")
        String tipo,

        @NotNull(message = "Data de aquisição é obrigatória")
        LocalDate dataAquisicao,

        @NotNull(message = "Número de série é obrigatório")
        int numSerie,
        
        @NotNull(message = "Título é obrigatório")
        UUID tituloId,

        List<UUID> locacoesIds
) {
}
