package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.ControleAcervoRequestDtos.ClasseRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.ControleAcervoResponseDtos.ClasseResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.ControleAcevoMappers.ClasseMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.ControleAcervo.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ControleAcervoServices.ClasseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("classes")
public class ClasseController extends BaseController<Classe, ClasseService, ClasseRequestDto, ClasseResponseDto> {

    private final ClasseMapper classeMapper;

    public ClasseController(ClasseService service, ClasseMapper classeMapper) {
        super(service);
        this.classeMapper = classeMapper;
    }

    @Override
    protected Classe toEntity(ClasseRequestDto requestDto) {
        return classeMapper.toEntity(requestDto);
    }

    @Override
    protected ClasseResponseDto toResponseDto(Classe entity) {
        return classeMapper.toResponseDto(entity);
    }
}
