package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Diretor;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.DiretorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiretorService extends BaseService<Diretor> {

    private final DiretorRepository diretorRepository;

    @Override
    protected IBaseRepository<Diretor> getRepository() {

        return this.diretorRepository;
    }
}
