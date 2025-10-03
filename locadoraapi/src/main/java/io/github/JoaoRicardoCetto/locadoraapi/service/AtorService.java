package io.github.JoaoRicardoCetto.locadoraapi.service;

import io.github.JoaoRicardoCetto.locadoraapi.model.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtorService {

    private final AtorRepository repository;

    public AtorService(AtorRepository repository) {
        this.repository = repository;
    }

    public Ator salvar(Ator ator){
        return repository.save(ator);
    }

    public void atualizar(Ator ator){
        if(ator.getId()!=null){
            throw new IllegalArgumentException("Para atualizar´e necessário que o ator esteja salvo no banco");
        }
        repository.save(ator);
    }

    public Optional<Ator> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Ator ator){
        repository.delete(ator);
    }

    public List<Ator> pesquisar(String nome){
        if(nome != null && !nome.isEmpty()){
            return repository.findByNome(nome);
        }

        return repository.findAll();
    }
}
