package io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.ClienteValidator;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.DependenteValidator;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.ClienteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.DependenteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.DependenteCreateException;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DependenteService extends BaseService<Dependente> {

    private final DependenteRepository dependenteRepository;
    private final DependenteValidator dependenteValidator;
    private final ClienteRepository clienteRepository;
    private final ClienteValidator clienteValidator;

    @Override
    protected IBaseRepository<Dependente> getRepository() {

        return dependenteRepository;
    }

    @Override
    public Dependente salvar(Dependente entity) {

        if(dependenteValidator.socioPossuiMaisQueTresDependentesAtivos(entity.getSocio().getId())){
            throw new DependenteCreateException("Não é possível vincular Dependente a um Sócio que possui 3 ou mais dependentes ativos");
        }

        entity.setNumInscricao(clienteRepository.count() + 1);

        return super.salvar(entity);
    }

    @Override
    public void deletar(Dependente entity) {
        if(clienteValidator.possuiLocacao(entity.getId())){
            throw new InvalidOperationException("Não é possível excluir Dependente que possui Locação");
        }
        super.deletar(entity);
    }

    public Dependente mudarAtividadeDependente(Dependente dependente){

        if(!dependente.getSocio().getEstahAtivo()){
            throw new InvalidOperationException("Não é possível ativar Dependente de um Sócio Desativado");
        }

        if(dependenteValidator.socioPossuiMaisQueTresDependentesAtivos(dependente.getSocio().getId()) && !dependente.getEstahAtivo()){
            throw new InvalidOperationException("Dependente não pode ser ativado, sócio possui 3 dependentes ativos");
        }

        dependente.setEstahAtivo(!dependente.getEstahAtivo());

        dependenteRepository.save(dependente);

        return dependente;
    }
}
