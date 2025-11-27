package io.github.JoaoRicardoCetto.locadoraapi.application.services.atendimentoClienteServices;

import io.github.JoaoRicardoCetto.locadoraapi.application.exceptions.InvalidOperationException;
import io.github.JoaoRicardoCetto.locadoraapi.application.services.common.BaseService;
import io.github.JoaoRicardoCetto.locadoraapi.application.validators.LocacaoValidator;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.common.IBaseRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.LocacaoRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Cliente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Classe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocacaoService extends BaseService<Locacao> {

    private final LocacaoRepository locacaoRepository;
    private final LocacaoValidator locacaoValidator;

    @Override
    protected IBaseRepository<Locacao> getRepository() {
        return locacaoRepository;
    }

    @Override
    public Locacao salvar(Locacao entity) {

        List<Locacao> locacoesAtrasadas = locacaoValidator.clientePossuiLocacoesAtrasadas(entity);

        if(!locacoesAtrasadas.isEmpty()) {
            throw new InvalidOperationException("Não é possível realizar nova locação. O cliente possui locações atrasadas: " +
                            locacoesAtrasadas
                                    .stream()
                                    .map(l -> " - Item: " + l.getItem().getTitulo().getNome() + " (Prevista: " + l.getDtDevolucaoPrevista() + ")")
                                    .collect(Collectors.joining("; "))
            );
        }

        Locacao locacaoComItemEscolhido = locacaoValidator.existeLocacaoEmAbertoComItemEscolhido(entity);

        if(locacaoComItemEscolhido != null){
            throw new InvalidOperationException(
                    "O Item está indisponível para locacao. Data prevista pra entrega: " +
                            locacaoComItemEscolhido.getDtDevolucaoPrevista()
            );
        }

        Classe classeTitulo = entity.getItem().getTitulo().getClasse();

        entity.setDtLocacao(LocalDate.now());
        entity.setDtDevolucaoPrevista(LocalDate.now().plusDays(classeTitulo.getPrazoDevolucao()));
        entity.setValorCobrado(classeTitulo.getValor());

        return super.salvar(entity);
    }

    @Override
    public Locacao atualizar(Locacao entity) {

        return locacaoRepository.save(entity);
    }

    @Override
    public void deletar(Locacao entity) {
        if(entity.getDtDevolucaoEfetiva() != null){
            throw new InvalidOperationException("Não é possível cancelar locação. A locação possui umpagamento e, portanto, não pode ser cancelada.");
        }
        super.deletar(entity);
    }

    public Locacao findByItemNumSerieAndDtDevolucaoEfetivaIsNull(int numSerie){

        Locacao locacao = locacaoRepository.findByItemNumSerieAndDtDevolucaoEfetivaIsNull(numSerie);

        if(locacao == null){
            new RuntimeException("Nenhuma locação em aberto para este item.");
        }

        return locacao;
    }

    public Locacao efetivarDevolucao(Locacao locacao){

        locacao.setDtDevolucaoEfetiva(LocalDate.now());

        locacaoRepository.save(locacao);

        return locacao;
    }

    public void atualizarValorMulta(Locacao locacao){
        if (locacaoValidator.locacaoEstaEmAtraso(locacao)) {
            locacao.setMultaCobrada(calcularMulta(locacao));

            locacao.setValorCobrado(locacao.getValorCobrado() + locacao.getMultaCobrada());

            locacaoRepository.save(locacao);
        }
    }

    public double calcularMulta(Locacao locacao){
        long diasAtrasadosLong = ChronoUnit.DAYS.between(
                locacao.getDtDevolucaoPrevista(),
                LocalDate.now()
        );

        int diasAtrasados = (int) diasAtrasadosLong;
        double valorMultaDiaria = locacao.getItem().getTitulo().getClasse().getValor() * 0.1;
        return diasAtrasados * valorMultaDiaria;
    }
}
