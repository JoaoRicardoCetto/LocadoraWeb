package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.ClasseRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.ClasseResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.ClasseMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.ClasseService;
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
