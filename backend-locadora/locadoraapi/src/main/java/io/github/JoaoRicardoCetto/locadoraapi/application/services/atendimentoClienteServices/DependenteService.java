package io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.DependenteValidator;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.DependenteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.exceptions.DependenteCreateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DependenteService extends BaseService<Dependente> {

    private final DependenteRepository dependenteRepository;
    private final DependenteValidator dependenteValidator;

    @Override
    protected IBaseRepository<Dependente> getRepository() {

        return dependenteRepository;
    }

    @Override
    public Dependente salvar(Dependente entity) {
        if(!dependenteValidator.socioPossuiMaisQueTresDependentes(entity)){
            throw new DependenteCreateException("Não é possível vincular Dependente a um Sócio que possui 3 ou mais dependentes");
        }

        return super.salvar(entity);
    }


}
