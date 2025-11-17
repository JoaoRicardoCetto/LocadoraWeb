package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.ClasseRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ClasseResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers.ClasseMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Classe;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.ClasseService;
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
