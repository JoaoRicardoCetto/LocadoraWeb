package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.AtendimentoClienteMappers;

import io.github.JoaoRicardoCetto.locadoraapi.Applications.Services.AtendimentoClienteServices.SocioService;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.AtendimentoClienteRequestDtos.DependenteRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos.DependenteResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos.SocioResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Socio;
import org.springframework.stereotype.Component;

@Component
public class DependenteMapper {

    private final SocioService socioService;

    public DependenteMapper(SocioService socioService) {
        this.socioService = socioService;
    }

    public Dependente toEntity(DependenteRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Socio socio = socioService.obterPorId(requestDto.socioId()).orElse(null);
        int numInscricao = Integer.parseInt(requestDto.numInscricao());
        return new Dependente(
                numInscricao,
                requestDto.nome(),
                requestDto.dtNascimento(),
                requestDto.sexo(),
                requestDto.estahAtivo(),
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
