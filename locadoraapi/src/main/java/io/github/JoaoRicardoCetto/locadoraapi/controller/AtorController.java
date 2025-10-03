package io.github.JoaoRicardoCetto.locadoraapi.controller;

import io.github.JoaoRicardoCetto.locadoraapi.controller.Dtos.AtorDto;
import io.github.JoaoRicardoCetto.locadoraapi.model.Ator;
import io.github.JoaoRicardoCetto.locadoraapi.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("atores")
public class AtorController {

    private final AtorService service;

    public AtorController(AtorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AtorDto ator){
        Ator atorEntity = ator.mapearParaAtor();
        service.salvar(atorEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(atorEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    };

    @GetMapping("{id}")
    public ResponseEntity<AtorDto> obterPorId(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Ator> ator = service.obterPorId(idAutor);
        if(ator.isPresent()){
            Ator entity = ator.get();

            AtorDto dto = new AtorDto(entity.getId(), entity.getNome());

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Ator> atorOptional = service.obterPorId(idAutor);

        if(atorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.deletar(atorOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AtorDto>> pesquisar(@RequestParam(value = "nome", required = false) String nome){

        List<Ator> resultado = service.pesquisar(nome);
        List<AtorDto> lista = resultado
                .stream()
                .map(ator -> new AtorDto(
                ator.getId(),
                ator.getNome())
                ).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable("id") String id, @RequestBody AtorDto dto){
        var idAutor = UUID.fromString(id);
        Optional<Ator> atorOptional = service.obterPorId(idAutor);

        if(atorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var ator = atorOptional.get();
        ator.setNome(dto.nome());

        service.atualizar(ator);

        return ResponseEntity.noContent().build();
    }
}
