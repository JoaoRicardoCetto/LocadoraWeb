package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.AtendimentoClienteServices.DependenteService;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.AtendimentoClienteRequestDtos.DependenteRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos.DependenteResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.AtendimentoClienteMappers.DependenteMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Dependente;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dependentes")
public class DependenteController extends BaseController<Dependente, DependenteService, DependenteRequestDto, DependenteResponseDto> {

    private final DependenteMapper dependenteMapper;

    public DependenteController(DependenteService service, DependenteMapper dependenteMapper) {
        super(service);
        this.dependenteMapper = dependenteMapper;
    }

    @Override
    protected Dependente toEntity(DependenteRequestDto requestDto) {
        return dependenteMapper.toEntity(requestDto);
    }

    @Override
    protected DependenteResponseDto toResponseDto(Dependente entity) {
        return dependenteMapper.toResponseDto(entity);
    }
}
