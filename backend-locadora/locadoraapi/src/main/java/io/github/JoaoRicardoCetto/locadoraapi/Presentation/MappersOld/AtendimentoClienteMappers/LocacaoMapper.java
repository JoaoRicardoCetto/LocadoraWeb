package io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.ClienteService;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.DependenteService;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.SocioService;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.controleAcervoServices.ItemService;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Cliente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.ControleAcevoMappers.ItemMapper;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.LocacaoRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.ClienteResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.LocacaoResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.controleAcervoResponseDtos.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocacaoMapper {

    private final ItemService itemService;
    private final ClienteService clienteService;
    private final SocioService socioService;
    private final DependenteService dependenteService;
    private final ItemMapper itemMapper;

    public Locacao toEntity(LocacaoRequestDto requestDto) {
        if(requestDto == null)  return null;

        Item item = itemService.obterPorId(requestDto.itemId())
                .orElseThrow(() -> new InvalidOperationException("Item informado na locação não foi encontrado."));

        Cliente cliente = resolveCliente(requestDto);

        if(cliente == null){
            throw new InvalidOperationException("Informe um Sócio ou Dependente válido para registrar a locação.");
        }

        return new Locacao(
                requestDto.dtLocacao(),
                requestDto.dtDevolucaoPrevista(),
                requestDto.dtDevolucaoEfetiva(),
                requestDto.valorCobrado(),
                requestDto.multaCobrada(),
                item,
                cliente
        );
    }

    public LocacaoResponseDto toResponseDto(Locacao entity){
        if (entity == null)  return null;

        ItemResponseDto itemResponseDto = itemMapper.toResponseDto(entity.getItem());
        Cliente cliente = entity.getCliente();

        if(cliente == null){
            throw new InvalidOperationException("Locação não possui cliente vinculado.");
        }

        String tipoCliente = "CLIENTE";
        if(cliente instanceof Socio){
            tipoCliente = "SOCIO";
        }else if(cliente instanceof Dependente){
            tipoCliente = "DEPENDENTE";
        }

        ClienteResponseDto clienteResponseDto =
                new ClienteResponseDto(
                    cliente.getId(),
                    cliente.getNumInscricao(),
                    cliente.getNome(),
                    cliente.getDataNascimento(),
                    cliente.getSexo(),
                    cliente.getEstahAtivo(),
                    null,
                    tipoCliente
                );

        return new LocacaoResponseDto(
                entity.getId(),
                entity.getDtLocacao(),
                entity.getDtDevolucaoPrevista(),
                entity.getDtDevolucaoEfetiva(),
                entity.getValorCobrado(),
                entity.getMultaCobrada(),
                itemResponseDto,
                clienteResponseDto
        );

    }

    private Cliente resolveCliente(LocacaoRequestDto requestDto){
        boolean hasClienteId = requestDto.clienteId() != null;
        boolean hasSocioId = requestDto.socioId() != null;
        boolean hasDependenteId = requestDto.dependenteId() != null;

        if((hasSocioId && hasDependenteId) || (hasClienteId && (hasSocioId || hasDependenteId))){
            throw new InvalidOperationException("Informe apenas um identificador de cliente (Sócio, Dependente ou Cliente).");
        }

        if(hasClienteId){
            return clienteService.obterPorId(requestDto.clienteId())
                    .orElseThrow(() -> new InvalidOperationException("Cliente informado não foi encontrado."));
        }

        if(hasSocioId){
            return socioService.obterPorId(requestDto.socioId())
                    .orElseThrow(() -> new InvalidOperationException("Sócio informado não foi encontrado."));
        }

        if(hasDependenteId){
            return dependenteService.obterPorId(requestDto.dependenteId())
                    .orElseThrow(() -> new InvalidOperationException("Dependente informado não foi encontrado."));
        }

        return null;
    }

}
