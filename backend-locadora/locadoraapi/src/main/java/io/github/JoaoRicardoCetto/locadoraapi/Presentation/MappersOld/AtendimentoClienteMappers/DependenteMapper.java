package io.github.JoaoRicardoCetto.locadoraapi.presentation.MappersOld.AtendimentoClienteMappers;

import io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices.SocioService;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.request.atendimentoClienteRequestDtos.DependenteRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.DependenteResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.presentation.dtos.response.atendimentoClienteResponseDtos.SocioResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DependenteMapper {

    private final SocioService socioService;

    public Dependente toEntity(DependenteRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Socio socio = socioService.obterPorId(requestDto.socioId()).orElse(null);

        return new Dependente(
                requestDto.nome(),
                requestDto.dtNascimento(),
                requestDto.sexo(),
                requestDto.estahAtivo(),
                null,
                socio
        );
    }

    public DependenteResponseDto toResponseDto(Dependente entity) {
        if (entity == null) {
            return null;
        }
        Socio socio = entity.getSocio();
        SocioResponseDto socioResponse = socio == null ? null : new SocioResponseDto(
                socio.getId(),
                String.valueOf(socio.getNumInscricao()),
                socio.getNome(),
                socio.getDataNascimento(),
                socio.getSexo(),
                socio.getEstahAtivo(),
                socio.getCpf(),
                socio.getEndereço(),
                socio.getTelefone(),
                null // evitar recursão: não listar dependentes aqui
        );

        return new DependenteResponseDto(
                entity.getId(),
                String.valueOf(entity.getNumInscricao()),
                entity.getNome(),
                entity.getDataNascimento(),
                entity.getSexo(),
                entity.getEstahAtivo(),
                socioResponse
        );
    }
}
