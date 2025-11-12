package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ControleAcervoRequestDtos.DiretorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ControleAcervoResponseDtos.DiretorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.ControleAcevoMappers.DiretorMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Diretor;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.DiretorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("diretores")
public class DiretorController extends BaseController<Diretor, DiretorService, DiretorRequestDto, DiretorResponseDto> {
    
    private final DiretorMapper diretorMapper;
    
    public DiretorController(DiretorService service, DiretorMapper diretorMapper) {
        super(service);
        this.diretorMapper = diretorMapper;
    }

    @Override
    protected Diretor toEntity(DiretorRequestDto requestDto) {
        return diretorMapper.toEntity(requestDto);
    }

    @Override
    protected DiretorResponseDto toResponseDto(Diretor entity) {
        return diretorMapper.toResponseDto(entity);
    }
}
