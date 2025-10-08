package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.*;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.TituloMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.TituloService;
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
