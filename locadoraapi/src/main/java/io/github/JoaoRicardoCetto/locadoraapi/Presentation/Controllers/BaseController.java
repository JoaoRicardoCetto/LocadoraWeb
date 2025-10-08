package io.github.JoaoRicardoCetto.locadoraapi.Presentation.Controllers;

import io.github.JoaoRicardoCetto.locadoraapi.model.Common.BaseEntity;
import io.github.JoaoRicardoCetto.locadoraapi.Applications.Common.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

// E = Entidade (Ex: Ator), S = Service (Ex: AtorService), RQ = Request DTO, RS = Response DTO
public abstract class BaseController<E extends BaseEntity,
        S extends BaseService<E>,
        RQ, RS> {

    protected final S service;

    public BaseController(S service) {
        this.service = service;
    }

    // 💡 Ganchos de Mapeamento: OBRIGA a subclasse a implementar a conversão
    protected abstract E toEntity(RQ requestDto);
    protected abstract RS toResponseDto(E entity);

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody RQ requestDto){
        E entity = toEntity(requestDto);
        service.salvar(entity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RS>> getAll(){
        List<E> entities = service.obterTodos();
        
        List<RS> responseDtos = entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<RS> obterPorId(@PathVariable("id") String id){
        var uuid = UUID.fromString(id);
        Optional<E> entityOptional = service.obterPorId(uuid);

        if(entityOptional.isPresent()){
            return ResponseEntity.ok(toResponseDto(entityOptional.get()));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        var uuid = UUID.fromString(id);
        Optional<E> entityOptional = service.obterPorId(uuid);

        if(entityOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.deletar(entityOptional.get());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @Valid @RequestBody RQ requestDto){
        var uuid = UUID.fromString(id);
        Optional<E> entityOptional = service.obterPorId(uuid);

        if(entityOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        E entity = toEntity(requestDto); // Mapeia o DTO para a entidade
        entity.setId(uuid);

        service.atualizar(entity);

        return ResponseEntity.noContent().build();
    }
}