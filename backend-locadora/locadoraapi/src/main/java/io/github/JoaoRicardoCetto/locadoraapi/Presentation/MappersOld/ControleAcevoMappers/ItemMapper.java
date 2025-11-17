package io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.ItemRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ItemResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.TituloService;
import org.springframework.stereotype.Component;

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

        Titulo titulo = tituloService.obterPorId(requestDto.tituloId()).orElse(null);

        Item item = new Item(
                requestDto.numSerie(),
                requestDto.dataAquisicao(),
                requestDto.tipo(),
                titulo
        );

        return item;
    }

    public ItemResponseDto toResponseDto(Item entity) {
        if (entity == null) {
            return null;
        }

        TituloResponseDto tituloResponse = mapTituloToResponse(entity.getTitulo());

        return new ItemResponseDto(
                entity.getId(),
                entity.getTipoItem(),
                entity.getDtAquisicao(),
                entity.getNumSerie(),
                tituloResponse
        );
    }

    private TituloResponseDto mapTituloToResponse(Titulo titulo) {
        if (titulo == null) {
            return null;
        }

        return new TituloResponseDto(
                titulo.getId(),
                titulo.getNome(),
                titulo.getAno(),
                titulo.getSinopse(),
                titulo.getCategoria(),
                null, // DiretorResponseDto (omitido para evitar recursão)
                null, // ClasseResponseDto (omitido)
                null, // outros campos aninhados (omitidos)
                null  // itens não incluídos aqui para evitar recursão
        );
    }
}
