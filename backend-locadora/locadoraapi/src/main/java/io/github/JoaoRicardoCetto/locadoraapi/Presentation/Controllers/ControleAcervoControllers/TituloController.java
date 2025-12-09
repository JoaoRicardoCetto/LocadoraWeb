package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.ControleAcervoControllers;

import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.controleAcervoRequestDtos.TituloRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.TituloResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers.TituloMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Titulo;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.TituloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/buscar")
    public ResponseEntity<List<TituloResponseDto>> buscar(
            @RequestParam(value = "termo", required = false, defaultValue = "") String termo,
            @RequestParam(value = "tipo", required = false, defaultValue = "todos") String tipo) {
        List<Titulo> titulos = service.buscar(termo, tipo);
        
        List<TituloResponseDto> responseDtos = titulos.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        long size = titulos.size();
        String contentRangeHeader = String.format("titulos 0-%d/%d", size - 1, size);

        return ResponseEntity.ok()
                .header("Content-Range", contentRangeHeader)
                .header("Access-Control-Expose-Headers", "Content-Range")
                .body(responseDtos);
    }
}
