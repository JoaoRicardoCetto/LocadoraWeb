package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.AtendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.AtendimentoClienteRepositories.DependenteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Dependente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DependenteService extends BaseService<Dependente> {

    private final DependenteRepository dependenteRepository;

    @Override
    protected IBaseRepository<Dependente> getRepository() {

        return dependenteRepository;
    }
}
