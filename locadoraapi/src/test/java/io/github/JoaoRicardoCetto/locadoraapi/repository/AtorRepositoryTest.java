package io.github.JoaoRicardoCetto.locadoraapi.repository;

import io.github.JoaoRicardoCetto.locadoraapi.model.Ator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AtorRepositoryTest {

    @Autowired
    AtorRepository repository;

    @Test
    public void salvarTest(){
        Ator a = new Ator();
        a.setNome("Joao Ricardo Cetto");

        var atorSalvo = repository.save(a);
        System.out.println("Ator " + a.toString() + " salvo com sucesso");
    }

    @Test
    public void atualizarTest(){
        Optional<Ator> possivelAtor = repository.findAll().stream().findFirst();

        if(possivelAtor.isPresent()) {
            Ator a = possivelAtor.get();
            System.out.println("Dados do ator: " + a.toString());

            a.setNome("updatedActor");
            repository.save(a);

            System.out.println("Ator " + a.toString() + " atualizado com sucesso");
        }

        System.out.println("Não existem atores cadastrados no sistema");
    }

    @Test
    public void listarTest(){
        List<Ator> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        long count = repository.count();
        System.out.println(count);
    }

    @Test
    public void deleteByIdTest(){
        Optional<Ator> possivelAtor = repository.findAll().stream().findFirst();

        if(possivelAtor.isPresent()) {
            Ator a = possivelAtor.get();
            var id = UUID.fromString(a.getId().toString());
            repository.deleteById(id);

            System.out.println("Ator " + a.toString() + " excluido com sucesso");
        }

        System.out.println("Não existem atores cadastrados no sistema");

    }
}
