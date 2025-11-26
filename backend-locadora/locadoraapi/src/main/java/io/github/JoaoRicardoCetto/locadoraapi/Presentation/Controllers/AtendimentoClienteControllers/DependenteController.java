package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.DependenteService;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.ClienteEstahAtivoRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.DependenteRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.DependenteResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers.DependenteMapper;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.SocioResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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

    @PatchMapping("estaAtivo/{id}")
    public ResponseEntity<?> mudarAtividadeDependente(
            @PathVariable("id") String id,
            @Valid @RequestBody ClienteEstahAtivoRequestDto requestDto) {

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build(); // id inválido
        }

        Optional<Dependente> opt = service.obterPorId(uuid);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Dependente entity = opt.get();

        try {
            Dependente saved = service.mudarAtividadeDependente(entity);
            DependenteResponseDto responseDto = toResponseDto(saved);

            return ResponseEntity.ok(responseDto);

        } catch (InvalidOperationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
