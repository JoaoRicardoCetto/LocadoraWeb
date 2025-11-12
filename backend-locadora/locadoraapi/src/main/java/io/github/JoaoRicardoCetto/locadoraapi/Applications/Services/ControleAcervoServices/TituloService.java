package io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ControleAcervoRepositories.ItemRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ControleAcervoRepositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ControleAcervoRepositories.TituloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TituloService extends BaseService<Titulo> {

    private final TituloRepository tituloRepository;
    private final AtorRepository atorRepository;
    private final ItemRepository itemRepository;

    @Override
    protected IBaseRepository<Titulo> getRepository() {
        return this.tituloRepository;
    }

    @Override
    @Transactional
    public Titulo salvar(Titulo entity) {
        Titulo saved = super.salvar(entity);

        if (saved.getItens() != null && !saved.getItens().isEmpty()) {
            saved.getItens().forEach(item -> {
                if (item.getTitulo() == null || !saved.equals(item.getTitulo())) {
                    item.setTitulo(saved);
                }
                itemRepository.save(item);
            });
        }

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
