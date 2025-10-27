package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

public record ItemRequestDto(
        @NotBlank(message = "Tipo é obrigatório")
        @Size(min = 2, max = 100, message = "Tipo deve ter entre 2 e 100 caracteres")
        String tipo,
        
        @NotNull(message = "Data de aquisição é obrigatória")
        LocalDate dataAquisicao,
        
        @NotNull(message = "Número de série é obrigatório")
        int numSerie,
        
        @NotNull(message = "ID do título é obrigatório")
        UUID tituloId
) {
}
