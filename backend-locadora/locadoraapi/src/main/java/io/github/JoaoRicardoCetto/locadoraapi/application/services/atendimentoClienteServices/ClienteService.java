package io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.ClienteValidator;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.ClienteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService extends BaseService<Cliente> {
    private final ClienteRepository clienteRepository;
    private final ClienteValidator clienteValidator;

    @Override
    protected IBaseRepository<Cliente> getRepository() {
        return clienteRepository;
    }
}
