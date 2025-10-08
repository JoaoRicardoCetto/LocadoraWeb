package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ClasseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ClasseService extends BaseService<Classe> {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository repository){

        this.classeRepository = repository;
    }

    @Override
    protected IBaseRepository<Classe> getRepository() {

        return this.classeRepository;
    }
}
