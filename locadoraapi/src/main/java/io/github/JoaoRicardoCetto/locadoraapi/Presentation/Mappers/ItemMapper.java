package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.ItemRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.ItemResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.DiretorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.ClasseResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Item;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.TituloService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemMapper {

    private final TituloService tituloService;

    public ItemMapper(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    public Item toEntity(ItemRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        
        Titulo titulo = null;
        if (requestDto.tituloId() != null) {
            titulo = tituloService.obterPorId(requestDto.tituloId()).orElse(null);
        }
        
        return new Item(
                0, // numSerie será gerado automaticamente
                requestDto.dataAquisicao(),
                requestDto.tipo(),
                titulo
        );
    }

    public ItemResponseDto toResponseDto(Item entity) {
        if (entity == null) {
            return null;
        }
        
        return new ItemResponseDto(
                entity.getId(),
                entity.getTipoItem(),
                entity.getDtAquisicao(),
                0.0, // Item não tem preço na entidade
                mapTitulo(entity.getTitulo())
        );
    }

    private TituloResponseDto mapTitulo(Titulo titulo) {
        if (titulo == null) {
            return null;
        }
        
        return new TituloResponseDto(
                titulo.getId(),
                titulo.getNome(),
                titulo.getAno(),
                titulo.getSinopse(),
                titulo.getCategoria(),
                mapDiretor(titulo.getDiretor()),
                mapClasse(titulo.getClasse()),
                null // Evitar recursão infinita
        );
    }

    private DiretorResponseDto mapDiretor(io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Diretor diretor) {
        if (diretor == null) {
            return null;
        }
        return new DiretorResponseDto(
                diretor.getId(),
                diretor.getNome(),
                null // Evitar recursão infinita
        );
    }

    private ClasseResponseDto mapClasse(io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Classe classe) {
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
}
