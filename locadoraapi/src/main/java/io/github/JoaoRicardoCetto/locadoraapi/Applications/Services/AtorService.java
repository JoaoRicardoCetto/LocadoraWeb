package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService extends BaseService<Ator> {

    private final AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {

        this.atorRepository = atorRepository;
    }

    @Override
    protected IBaseRepository<Ator> getRepository() {

        return this.atorRepository;
    }

    public List<Ator> pesquisar(String nome){
        if(nome != null && !nome.isEmpty()){
            return atorRepository.findByNome(nome);
        }

        return getRepository().findAll();
    }
}
