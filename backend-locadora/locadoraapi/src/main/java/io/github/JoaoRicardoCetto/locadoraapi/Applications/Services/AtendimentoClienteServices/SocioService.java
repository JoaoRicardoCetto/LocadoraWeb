package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.AtendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.AtendimentoClienteRepositories.SocioRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Socio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocioService extends BaseService<Socio> {

    private final SocioRepository socioRepository;

    @Override
    protected IBaseRepository<Socio> getRepository() {

        return socioRepository;
    }
}
