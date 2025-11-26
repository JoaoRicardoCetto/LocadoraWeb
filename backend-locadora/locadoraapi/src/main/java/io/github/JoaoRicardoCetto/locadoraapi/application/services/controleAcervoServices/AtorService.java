package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.AtorDeleteException;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.AtorValidator;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtorService extends BaseService<Ator> {

    private final AtorRepository repository;
    private final AtorValidator validator;

    @Override
    protected IBaseRepository<Ator> getRepository() {

        return this.repository;
    }

    @Override
    public Ator salvar(Ator entity) {
        if(!validator.validar(entity)){
            throw new InvalidOperationException("O campo Nome do Ator deve ter mais de 2 caracteres");
        }
        return super.salvar(entity);
    }

    @Override
    public void deletar(Ator entity) {
        if(validator.possuiTitulo(entity.getId())){
            throw new AtorDeleteException("Não é possível excluir Ator vinculado a um ou mais Títulos");
        }
        super.deletar(entity);
    }

}
