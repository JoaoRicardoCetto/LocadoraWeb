package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Mappers.AtendimentoClienteMappers;

import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Request.AtendimentoClienteRequestDtos.SocioRequestDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos.DependenteResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.Presentation.Dtos.Response.AtendimentoClienteResponseDtos.SocioResponseDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.Entities.AtendimentoCliente.Socio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SocioMapper {

    public Socio toEntity(SocioRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        int numInscricao = Integer.parseInt(requestDto.numInscricao());
        return new Socio(
                numInscricao,
                requestDto.nome(),
                requestDto.dtNascimento(),
                requestDto.sexo(),
                requestDto.estahAtivo(),
                requestDto.cpf(),
                requestDto.endereco(),
                requestDto.telefone(),
                null // dependentes criados/associados separadamente
        );
    }

    public SocioResponseDto toResponseDto(Socio entity) {
        if (entity == null) {
            return null;
        }
        List<Dependente> dependentes = entity.getDependentes();
        List<DependenteResponseDto> dependentesResponse = dependentes == null ? null :
                dependentes.stream().map(dep -> new DependenteResponseDto(
                        dep.getId(),
                        String.valueOf(dep.getNumInscricao()),
                        dep.getNome(),
                        dep.getDataNascimento(),
                        dep.getSexo(),
                        dep.getEstahAtivo(),
                        null // evitar recursão: não incluir o socio dentro do dependente
                )).collect(Collectors.toList());

        return new SocioResponseDto(
                entity.getId(),
                String.valueOf(entity.getNumInscricao()),
                entity.getNome(),
                entity.getDataNascimento(),
                entity.getSexo(),
                entity.getEstahAtivo(),
                entity.getCpf(),
                entity.getEndereço(),
                entity.getTelefone(),
                dependentesResponse
        );
    }
}
