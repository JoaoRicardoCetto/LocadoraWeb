package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.DiretorDeleteException;
import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.DiretorValidator;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Diretor;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.DiretorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiretorService extends BaseService<Diretor> {

    private final DiretorRepository diretorRepository;
    private final DiretorValidator diretorValidator;

    @Override
    protected IBaseRepository<Diretor> getRepository() {

        return this.diretorRepository;
    }

    @Override
    public void deletar(Diretor entity) {
        if(diretorValidator.possuiTitulo(entity.getId())){
            throw new DiretorDeleteException("Não é possível excluir Diretor vinculado a um ou mais Títulos");
        }

        super.deletar(entity);
    }
}
