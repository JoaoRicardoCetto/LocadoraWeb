package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ItemRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.TituloRepository;
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
