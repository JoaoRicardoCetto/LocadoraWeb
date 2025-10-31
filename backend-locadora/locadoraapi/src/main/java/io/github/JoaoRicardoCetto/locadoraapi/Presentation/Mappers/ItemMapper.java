package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ItemRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ItemResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.DiretorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ClasseResponseDto;
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
        
        return new Item(
                requestDto.numSerie(), // numSerie será gerado automaticamente
                requestDto.dataAquisicao(),
                requestDto.tipo(),
                null // Título será associado posteriormente
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
                entity.getNumSerie(), // Item não tem preço na entidade
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
                null, // Evitar recursão infinita
                null  // itens não incluídos aqui para evitar ciclos
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
