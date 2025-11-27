package io.github.JoaoRicardoCetto.locadoraapi.application.validators;

import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.ClienteRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.atendimentoClienteRepositories.LocacaoRepository;
import io.github.JoaoRicardoCetto.locadoraapi.infrastructure.repositories.controleAcervoRepositories.ItemRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Cliente;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.atendimentoCliente.Locacao;
import io.github.JoaoRicardoCetto.locadoraapi.model.entities.controleAcervo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocacaoValidator {
    private final ClienteRepository clienteRepository;
    private final LocacaoRepository  locacaoRepository;
    private final ItemRepository  itemRepository;

    public List<Locacao> clientePossuiLocacoesAtrasadas(Locacao entity) {

        Cliente cliente = entity.getCliente();

        List<Locacao> locacoesAtrasadas = cliente.getLocacoes()
                .stream()
                .filter(l -> l.getDtDevolucaoPrevista().isBefore(LocalDate.now()))
                .filter(l -> l.getDtDevolucaoEfetiva() == null)
                .toList();

        return locacoesAtrasadas;
    }

    public Locacao existeLocacaoEmAbertoComItemEscolhido(Locacao entity) {
        return locacaoRepository.findByItemId(entity.getItem().getId())
                .stream()
                .filter(l -> l.getDtDevolucaoEfetiva() == null)
                .findFirst()
                .orElse(null);
    }

    public boolean locacaoEstaEmAtraso(Locacao entity) {
        return LocalDate.now().isAfter(entity.getDtDevolucaoPrevista());
    }
}
