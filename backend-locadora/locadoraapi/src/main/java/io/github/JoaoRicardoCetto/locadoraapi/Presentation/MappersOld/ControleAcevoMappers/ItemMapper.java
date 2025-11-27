package io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.LocacaoService;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.LocacaoRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.ItemRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ItemResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.TituloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final TituloService tituloService;
    private final LocacaoRepository locacaoRepository;

    public Item toEntity(ItemRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        Titulo titulo = tituloService.obterPorId(requestDto.tituloId()).orElse(null);

        List<Locacao> locacoes = requestDto.locacoesIds()
                .stream()
                .map(id -> locacaoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Locação não encontrada: " + id)))
                .toList();

        Item item = new Item(
                requestDto.numSerie(),
                requestDto.dataAquisicao(),
                requestDto.tipo(),
                titulo,
                locacoes
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
