package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.AtorRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtorResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.AtorMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.AtorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<AtorResponseDto>> pesquisar(@RequestParam(value = "nome", required = false) String nome){
        List<Ator> resultado = service.pesquisar(nome);
        
        List<AtorResponseDto> lista = resultado.stream()
                .map(atorMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
