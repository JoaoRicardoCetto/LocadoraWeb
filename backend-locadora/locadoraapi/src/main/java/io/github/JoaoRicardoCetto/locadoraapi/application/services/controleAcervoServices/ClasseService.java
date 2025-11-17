package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ClasseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
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
