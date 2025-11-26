package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.ClasseDeleteException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.ClasseValidator;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ClasseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClasseService extends BaseService<Classe> {

    private final ClasseRepository repository;
    private final ClasseValidator validator;

    @Override
    protected IBaseRepository<Classe> getRepository() {

        return this.repository;
    }

    @Override
    public void deletar(Classe entity) {
        if(validator.possuiTitulo(entity.getId())){
            throw new ClasseDeleteException("Não é possível excluir Classe vinculada a um ou mais Títulos");
        }

        super.deletar(entity);
    }
}
