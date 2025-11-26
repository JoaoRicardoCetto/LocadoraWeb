package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.LocacaoService;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.model.common.BaseEntity;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers.LocacaoMapper;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.LocacaoRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.LocacaoResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locacao")
public class LocacaoController extends BaseController<Locacao, LocacaoService, LocacaoRequestDto, LocacaoResponseDto> {

    private final LocacaoMapper locacaoMapper;

    public LocacaoController(LocacaoService service, LocacaoMapper locacaoMapper) {
        super(service);
        this.locacaoMapper = locacaoMapper;
    }

    @Override
    protected Locacao toEntity(LocacaoRequestDto requestDto) {
        return locacaoMapper.toEntity(requestDto);
    }

    @Override
    protected LocacaoResponseDto toResponseDto(Locacao entity) {
        return locacaoMapper.toResponseDto(entity);
    }

}
