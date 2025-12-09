package io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.TituloDeleteException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.TituloValidator;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ItemRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.AtorRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.TituloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TituloService extends BaseService<Titulo> {

    private final TituloRepository tituloRepository;
    private final AtorRepository atorRepository;
    private final ItemRepository itemRepository;
    private final TituloValidator tituloValidator;

    @Override
    protected IBaseRepository<Titulo> getRepository() {
        return this.tituloRepository;
    }

    public List<Titulo> buscarPorNomeTituloClasseOuAutor(String termo) {
        // Combina resultados de múltiplas buscas, removendo duplicatas
        Set<Titulo> titulosUnicos = new LinkedHashSet<>();
        
        // Busca por nome do título
        titulosUnicos.addAll(tituloRepository.findByNomeContainingIgnoreCase(termo));
        
        // Busca por nome da classe
        titulosUnicos.addAll(tituloRepository.findByClasseNomeContainingIgnoreCase(termo));
        
        // Busca por nome do ator
        titulosUnicos.addAll(tituloRepository.findByAtoresNomeContainingIgnoreCase(termo));
        
        return List.copyOf(titulosUnicos);
    }
    
    public List<Titulo> buscar(String termo, String tipo) {
        if (termo == null || termo.trim().isEmpty()) {
            return obterTodos();
        }
        
        String tipoBusca = tipo != null ? tipo.toLowerCase() : "todos";
        
        if ("titulo".equals(tipoBusca)) {
            return tituloRepository.findByNomeContainingIgnoreCase(termo);
        } else if ("classe".equals(tipoBusca)) {
            return tituloRepository.findByClasseNomeContainingIgnoreCase(termo);
        } else if ("ator".equals(tipoBusca)) {
            return tituloRepository.findByAtoresNomeContainingIgnoreCase(termo);
        } else if ("categoria".equals(tipoBusca)) {
            return tituloRepository.findByCategoriaContainingIgnoreCase(termo);
        } else {
            return buscarPorNomeTituloClasseOuAutor(termo);
        }
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

    @Override
    public void deletar(Titulo entity) {
        if(tituloValidator.possuiItem(entity.getId())){
            throw new TituloDeleteException("Não é possível excluir Títulos vinculado a um ou mais Itens");
        }

        super.deletar(entity);
    }
}
