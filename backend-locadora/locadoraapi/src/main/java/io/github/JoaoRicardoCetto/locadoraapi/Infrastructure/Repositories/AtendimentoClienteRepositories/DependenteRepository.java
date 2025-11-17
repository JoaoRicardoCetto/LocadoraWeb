package io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;

import java.util.List;
import java.util.UUID;

public interface DependenteRepository extends IBaseRepository<Dependente> {
    List<Dependente> findBySocioId (UUID socioId);
}
