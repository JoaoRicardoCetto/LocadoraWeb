package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.AtorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.AtorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers.AtorMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("atores")
public class AtorController extends BaseController<Ator, AtorService, AtorRequestDto, AtorResponseDto> {

    private final AtorMapper atorMapper;

    public AtorController(AtorService service, AtorMapper atorMapper) {
        super(service);
        this.atorMapper = atorMapper;
    }

    @Override
    protected Ator toEntity(AtorRequestDto requestDto) {
        return atorMapper.toEntity(requestDto);
    }

    @Override
    protected AtorResponseDto toResponseDto(Ator entity) {
        return atorMapper.toResponseDto(entity);
    }

}
