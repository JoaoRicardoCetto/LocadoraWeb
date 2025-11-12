package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.AtendimentoClienteServices.SocioService;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.AtendimentoClienteRequestDtos.SocioRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos.SocioResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.AtendimentoClienteMappers.SocioMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Socio;
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
