package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.SocioService;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.SocioRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.SocioResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers.SocioMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("socios")
public class SocioController extends BaseController<Socio, SocioService, SocioRequestDto, SocioResponseDto> {

    private final SocioMapper socioMapper;

    public SocioController(SocioService service, SocioMapper socioMapper) {
        super(service);
        this.socioMapper = socioMapper;
    }

    @Override
    protected Socio toEntity(SocioRequestDto requestDto) {
        return socioMapper.toEntity(requestDto);
    }

    @Override
    protected SocioResponseDto toResponseDto(Socio entity) {
        return socioMapper.toResponseDto(entity);
    }
}
