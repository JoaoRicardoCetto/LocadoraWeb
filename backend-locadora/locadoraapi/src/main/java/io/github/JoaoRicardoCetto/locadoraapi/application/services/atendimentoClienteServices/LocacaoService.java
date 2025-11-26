package io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.LocacaoRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocacaoService extends BaseService<Locacao> {

    private final LocacaoRepository locacaoRepository;

    @Override
    protected IBaseRepository<Locacao> getRepository() {
        return locacaoRepository;
    }
}
