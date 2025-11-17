package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.DependenteService;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.DependenteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DependenteValidator {

    private final DependenteRepository dependenteRepository;

    public boolean socioPossuiMaisQueTresDependentes(Dependente dependente){
        List<Dependente> dependentes = dependenteRepository.findBySocioId(dependente.getSocio().getId());
        if(dependentes.size() > 3) {
            return true;
        }
        return false;
    }

}
