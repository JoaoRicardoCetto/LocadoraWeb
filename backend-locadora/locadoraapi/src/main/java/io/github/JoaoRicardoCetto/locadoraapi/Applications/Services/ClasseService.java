package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ClasseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClasseService extends BaseService<Classe> {

    private final ClasseRepository classeRepository;

    @Override
    protected IBaseRepository<Classe> getRepository() {

        return this.classeRepository;
    }
}
