package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.exceptions.AtorDeleteException;
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

    private final AtorRepository atorRepository;
    private final AtorValidator validator;

    @Override
    protected IBaseRepository<Ator> getRepository() {

        return this.atorRepository;
    }

    @Override
    public void deletar(Ator entity) {
        if(validator.possuiTitulo(entity)){
            throw new AtorDeleteException("Não é possível excluir Ator vinculado a um ou mais Títulos");
        }
        super.deletar(entity);
    }

    public List<Ator> pesquisar(String nome){
        if(nome != null && !nome.isEmpty()){
            return atorRepository.findByNome(nome);
        }

        return getRepository().findAll();
    }
}
