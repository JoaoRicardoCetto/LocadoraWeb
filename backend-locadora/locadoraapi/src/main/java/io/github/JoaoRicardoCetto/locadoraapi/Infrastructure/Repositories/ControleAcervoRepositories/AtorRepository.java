package io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Repositories.ControleAcervoRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.Infrastructure.Common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Ator;
import java.util.List;

public interface AtorRepository extends IBaseRepository<Ator> {
    List<Ator> findByNome(String nome);

}
