package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.TituloRepository;
import org.springframework.stereotype.Service;

@Service
public class TituloService extends BaseService<Titulo> {

    private final TituloRepository tituloRepository;

    public TituloService(TituloRepository tituloRepository) {

        this.tituloRepository = tituloRepository;
    }

    @Override
    protected IBaseRepository<Titulo> getRepository() {
        return this.tituloRepository;
    }
}
