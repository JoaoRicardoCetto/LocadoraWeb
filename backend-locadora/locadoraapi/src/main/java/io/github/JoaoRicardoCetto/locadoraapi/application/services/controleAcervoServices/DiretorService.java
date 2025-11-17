package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Diretor;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.DiretorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
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
