package io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.UUID;

public interface TituloRepository extends IBaseRepository<Titulo> {
    boolean existsByAtoresId(UUID atorId);
    boolean existsByClasseId(UUID classeId);
    boolean existsByDiretorId(UUID diretorId);
    
    @EntityGraph(attributePaths = {"classe", "atores", "diretor", "itens"})
    List<Titulo> findByNomeContainingIgnoreCase(String termo);
    
    @EntityGraph(attributePaths = {"classe", "atores", "diretor", "itens"})
    List<Titulo> findByCategoriaContainingIgnoreCase(String termo);
    
    @EntityGraph(attributePaths = {"classe", "atores", "diretor", "itens"})
    List<Titulo> findByClasseNomeContainingIgnoreCase(String termo);
    
    @EntityGraph(attributePaths = {"classe", "atores", "diretor", "itens"})
    List<Titulo> findByAtoresNomeContainingIgnoreCase(String termo);
}
