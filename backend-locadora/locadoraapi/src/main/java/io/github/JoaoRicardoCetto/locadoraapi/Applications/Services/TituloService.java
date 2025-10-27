package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.TituloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class TituloService extends BaseService<Titulo> {

    private final TituloRepository tituloRepository;
    private final AtorRepository atorRepository;

    public TituloService(TituloRepository tituloRepository, AtorRepository atorRepository) {
        this.tituloRepository = tituloRepository;
        this.atorRepository = atorRepository;
    }

    @Override
    protected IBaseRepository<Titulo> getRepository() {
        return this.tituloRepository;
    }

    @Override
    @Transactional
    public Titulo salvar(Titulo entity) {
        // Salva o título primeiro
        Titulo saved = super.salvar(entity);

        // Garante consistência bidirecional: adiciona o título na coleção de cada ator e salva os atores
        Set<Ator> atores = saved.getAtores();
        if (atores != null && !atores.isEmpty()) {
            for (Ator ator : atores) {
                ator.getTitulos().add(saved);
                atorRepository.save(ator);
            }
        }

        return saved;
    }
}
