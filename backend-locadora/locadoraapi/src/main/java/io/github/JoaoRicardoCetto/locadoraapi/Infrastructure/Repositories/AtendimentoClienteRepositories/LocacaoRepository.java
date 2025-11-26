package io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;

import java.util.UUID;

public interface LocacaoRepository extends IBaseRepository<Locacao> {
    boolean existsByClienteId(UUID clienteId);
}
