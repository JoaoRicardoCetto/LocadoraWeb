package io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.ClienteValidator;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.ClienteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.DependenteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.SocioRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Dependente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Socio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioService extends BaseService<Socio> {

    private final SocioRepository socioRepository;
    private final ClienteRepository clienteRepository;
    private final DependenteRepository dependenteRepository;
    private final ClienteValidator clienteValidator;

    @Override
    protected IBaseRepository<Socio> getRepository() {

        return socioRepository;
    }

    @Override
    public Socio salvar(Socio entity) {

        entity.setNumInscricao(clienteRepository.count() + 1);

        return super.salvar(entity);
    }

    @Override
    public void deletar(Socio entity) {

        if(clienteValidator.possuiLocacao(entity.getId())){
            throw new InvalidOperationException("Não é possível excluir sócio que possui Locação");
        }

        List<Dependente> dependentes = entity.getDependentes();

        dependenteRepository.deleteAll(dependentes);

        super.deletar(entity);
    }

    public Socio mudarAtividadeSocio(Socio socio){

        socio.setEstahAtivo(!socio.getEstahAtivo());

        List<Dependente> dependentes = socio.getDependentes();

        if(socio.getEstahAtivo() == false){
            dependentes.forEach(d -> d.setEstahAtivo(false));
        }

        else{
            for (int i = 0; i < dependentes.size() && i < 3; i++) {
                Dependente d = dependentes.get(i);
                d.setEstahAtivo(!d.getEstahAtivo());
            }
        }

        dependenteRepository.saveAll(dependentes);

        socioRepository.save(socio);

        return socio;
    }
}
