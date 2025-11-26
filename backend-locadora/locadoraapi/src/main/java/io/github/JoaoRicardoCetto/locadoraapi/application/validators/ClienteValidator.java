package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.ClienteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.LocacaoRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClienteValidator {
    private final ClienteRepository clienteRepository;
    private final LocacaoRepository locacaoRepository;

    public boolean possuiLocacao(UUID clienteId) {
        return locacaoRepository.existsByClienteId(clienteId);
    }
}
