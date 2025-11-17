package io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;

public interface TituloRepository extends IBaseRepository<Titulo> {
    boolean existsByAtores(Ator ator);

}
