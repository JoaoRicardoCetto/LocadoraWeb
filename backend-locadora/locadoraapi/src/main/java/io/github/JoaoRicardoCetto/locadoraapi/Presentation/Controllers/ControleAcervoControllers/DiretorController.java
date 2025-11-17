package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.DiretorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.DiretorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers.DiretorMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Diretor;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.DiretorService;
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
