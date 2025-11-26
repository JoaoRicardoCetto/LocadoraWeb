package io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;

import java.util.UUID;

public interface TituloRepository extends IBaseRepository<Titulo> {
    boolean existsByAtoresId(UUID atorId);
    boolean existsByClasseId(UUID classeId);
    boolean existsByDiretorId(UUID diretorId);
}
