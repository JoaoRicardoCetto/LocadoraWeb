package io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ControleAcervoRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Titulo;

public interface TituloRepository extends IBaseRepository<Titulo> {
    boolean existsByAtores(Ator ator);

}
