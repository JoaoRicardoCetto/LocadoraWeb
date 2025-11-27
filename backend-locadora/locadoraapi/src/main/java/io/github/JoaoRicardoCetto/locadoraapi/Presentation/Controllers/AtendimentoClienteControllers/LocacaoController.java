package io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.AtendimentoClienteControllers;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.LocacaoService;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.ItemService;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ItemRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.common.BaseEntity;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers.LocacaoMapper;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.controllers.BaseController;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.LocacaoDevolvidaRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.LocacaoRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.LocacaoResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.SocioResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("locacao")
public class LocacaoController extends BaseController<Locacao, LocacaoService, LocacaoRequestDto, LocacaoResponseDto> {

    private final LocacaoMapper locacaoMapper;
    private final ItemService  itemService;
    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService service, LocacaoMapper locacaoMapper, ItemService itemService, LocacaoService locacaoService) {
        super(service);
        this.locacaoMapper = locacaoMapper;
        this.itemService = itemService;
        this.locacaoService = locacaoService;
    }

    @Override
    protected Locacao toEntity(LocacaoRequestDto requestDto) {
        return locacaoMapper.toEntity(requestDto);
    }

    @Override
    protected LocacaoResponseDto toResponseDto(Locacao entity) {
        return locacaoMapper.toResponseDto(entity);
    }

    @GetMapping("devolucao/localizar")
    public ResponseEntity<?> localizarPorNumeroSerie(@RequestParam int numSerie) {

        try {
            Item item = itemService.findByNumSerie(numSerie);
            Locacao locacaoVigente = service
                    .findByItemNumSerieAndDtDevolucaoEfetivaIsNull(item.getNumSerie());

            if(locacaoVigente != null) locacaoService.atualizarValorMulta(locacaoVigente);

            LocacaoResponseDto locacaoResponseDto = locacaoMapper.toResponseDto(locacaoVigente);

            return ResponseEntity.ok(locacaoResponseDto);

        } catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PatchMapping("devolucao/efetivar/{id}")
    public ResponseEntity<?> devolucaoLocacao(
            @PathVariable("id") String id,
            @Valid @RequestBody LocacaoDevolvidaRequestDto locacaoDevolvidaRequestDto) {

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build(); // id inválido
        }

        Optional<Locacao> opt = service.obterPorId(uuid);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Locacao entity = opt.get();

        Locacao saved = service.efetivarDevolucao(entity);

        LocacaoResponseDto responseDto = toResponseDto(saved);

        return ResponseEntity.ok(responseDto);

    }

}
