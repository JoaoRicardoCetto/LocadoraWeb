package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.SocioService;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.ClienteEstahAtivoRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.SocioRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.SocioResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers.SocioMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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

    @PatchMapping("estaAtivo/{id}")
    public ResponseEntity<SocioResponseDto> mudarAtividadeSocio(
            @PathVariable("id") String id,
            @Valid @RequestBody ClienteEstahAtivoRequestDto requestDto) {

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build(); // id inválido
        }

        Optional<Socio> opt = service.obterPorId(uuid);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Socio entity = opt.get();

        Socio saved = service.mudarAtividadeSocio(entity);

        SocioResponseDto responseDto = toResponseDto(saved);
        return ResponseEntity.ok(responseDto);
    }

}
