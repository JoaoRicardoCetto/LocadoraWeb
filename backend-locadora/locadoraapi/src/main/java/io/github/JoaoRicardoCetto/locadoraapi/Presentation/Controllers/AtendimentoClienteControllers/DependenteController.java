package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.DependenteService;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.DependenteRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.DependenteResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers.DependenteMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.exceptions.DependenteCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
