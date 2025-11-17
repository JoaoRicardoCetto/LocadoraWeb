package io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.SocioRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
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
