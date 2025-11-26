package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.DependenteService;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.DependenteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DependenteValidator {

    private final DependenteRepository dependenteRepository;

    public boolean socioPossuiMaisQueTresDependentesAtivos(UUID socioId){

        long count = dependenteRepository.countBySocioIdAndEstahAtivoTrue(socioId);

        return count >= 3;
    }

}
