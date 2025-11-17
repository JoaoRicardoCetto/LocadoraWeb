package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.TituloRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers.TituloMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.TituloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("titulos")
public class TituloController extends BaseController<Titulo, TituloService, TituloRequestDto, TituloResponseDto> {
    
    private final TituloMapper tituloMapper;

    public TituloController(TituloService service, TituloMapper tituloMapper) {
        super(service);
        this.tituloMapper = tituloMapper;
    }

    @Override
    protected Titulo toEntity(TituloRequestDto requestDto) {
        return tituloMapper.toEntity(requestDto);
    }

    @Override
    protected TituloResponseDto toResponseDto(Titulo entity) {
        return tituloMapper.toResponseDto(entity);
    }
}
