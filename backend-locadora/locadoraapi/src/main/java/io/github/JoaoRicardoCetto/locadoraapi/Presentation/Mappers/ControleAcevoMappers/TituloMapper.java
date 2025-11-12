package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.ControleAcevoMappers;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.ItemService;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ControleAcervoRequestDtos.TituloRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.DiretorService;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.ClasseService;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.AtorService;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ControleAcervoResponseDtos.*;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TituloMapper {

    private final DiretorService diretorService;
    private final ClasseService classeService;
    private final AtorService atorService;
    private final ItemService itemService;

    public TituloMapper(DiretorService diretorService, ClasseService classeService, AtorService atorService, ItemService itemService) {
        this.diretorService = diretorService;
        this.classeService = classeService;
        this.atorService = atorService;
        this.itemService = itemService;
    }

    public Titulo toEntity(TituloRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        
        // Buscar as entidades relacionadas pelos IDs
        Diretor diretor = null;
        if (requestDto.diretorId() != null) {
            diretor = diretorService.obterPorId(requestDto.diretorId()).orElse(null);
        }
        
        Classe classe = null;
        if (requestDto.classeId() != null) {
            classe = classeService.obterPorId(requestDto.classeId()).orElse(null);
        }
        
        Set<Ator> atores = new HashSet<>();
        if (requestDto.atoresIds() != null) {
            atores = requestDto.atoresIds().stream()
                    .map(atorService::obterPorId)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }

        List<Item> itens = new ArrayList<>();
        if (requestDto.itensIds() != null) {
            itens = requestDto.itensIds().stream()
                    .map(itemService::obterPorId)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }

        return new Titulo(
                requestDto.nome(),
                requestDto.ano(),
                requestDto.sinopse(),
                requestDto.categoria(),
                diretor,
                classe,
                atores,
                itens
        );
    }

    public TituloResponseDto toResponseDto(Titulo entity) {
        if (entity == null) {
            return null;
        }
        return new TituloResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getAno(),
                entity.getSinopse(),
                entity.getCategoria(),
                mapDiretor(entity.getDiretor()),
                mapClasse(entity.getClasse()),
                mapAtores(entity.getAtores()),
                mapItens(entity.getItens())
        );
    }

    private DiretorResponseDto mapDiretor(Diretor diretor) {
        if (diretor == null) {
            return null;
        }
        return new DiretorResponseDto(
                diretor.getId(),
                diretor.getNome(),
                null // Evitar recursão infinita
        );
    }

    private ClasseResponseDto mapClasse(Classe classe) {
        if (classe == null) {
            return null;
        }
        return new ClasseResponseDto(
                classe.getId(),
                classe.getNome(),
                classe.getValor(),
                classe.getPrazoDevolucao(),
                null // Evitar recursão infinita
        );
    }

    private Set<AtorResponseDto> mapAtores(Set<Ator> atores) {
        if (atores == null) {
            return null;
        }
        return atores.stream()
                .map(ator -> new AtorResponseDto(
                        ator.getId(),
                        ator.getNome(),
                        null // Evitar recursão infinita
                ))
                .collect(Collectors.toSet());
    }

    private List<ItemResponseDto> mapItens(List<Item> itens) {
        if (itens == null) {
            return null;
        }
        return itens.stream()
                .map(item -> new ItemResponseDto(
                        item.getId(),
                        item.getTipoItem(),
                        item.getDtAquisicao(),
                        item.getNumSerie(),
                        null // Evitar recursão: não incluir o título completo aqui
                ))
                .collect(Collectors.toList());
    }
}
