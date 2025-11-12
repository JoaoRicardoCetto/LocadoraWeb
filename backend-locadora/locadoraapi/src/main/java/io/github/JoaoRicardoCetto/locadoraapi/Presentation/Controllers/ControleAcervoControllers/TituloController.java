package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ControleAcervoRequestDtos.TituloRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ControleAcervoResponseDtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.ControleAcevoMappers.TituloMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.TituloService;
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
