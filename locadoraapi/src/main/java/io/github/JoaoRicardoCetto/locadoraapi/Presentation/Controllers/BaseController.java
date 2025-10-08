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

// E = Entidade (Ex: Ator), S = Service (Ex: AtorService), request = Request DTO, response = Response DTO
public abstract class BaseController
        < E extends BaseEntity,
        S extends BaseService<E>,
        request,
        response >
{

    protected final S service;

    public BaseController(S service) {
        this.service = service;
    }

    // 💡 Ganchos de Mapeamento: OBRIGA a subclasse a implementar a conversão
    protected abstract E toEntity(request requestDto);
    protected abstract response toResponseDto(E entity);

    @PostMapping
    public ResponseEntity<response> salvar(@Valid @RequestBody request requestDto){
        E entity = toEntity(requestDto);
        service.salvar(entity);

        response responseDto = toResponseDto(entity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<response>> getAll(){
        List<E> entities = service.obterTodos();
        
        List<response> responseDtos = entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        long size = entities.size();
        String resourceName = entities.getClass().toString(); // Substitua pelo nome do recurso (ex: atores)

        String contentRangeHeader = String.format("%s 0-%d/%d", resourceName, size - 1, size);

        return ResponseEntity.ok()
                // 💡 HEADER ALTERNATIVO: Content-Range
                .header("Content-Range", contentRangeHeader)
                // Se o seu frontend estiver em um domínio diferente:
                // .header("Access-Control-Expose-Headers", "Content-Range")
                .body(responseDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<response> obterPorId(@PathVariable("id") String id){
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
    public ResponseEntity<response> atualizar(@PathVariable("id") String id, @Valid @RequestBody request requestDto){
        var uuid = UUID.fromString(id);

        Optional<E> entityOptional = service.obterPorId(uuid);

        if(entityOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        E entity = toEntity(requestDto); // Mapeia o DTO para a entidade
        entity.setId(uuid);

        service.atualizar(entity);

        response responseDto = toResponseDto(entity);

        return ResponseEntity.ok(responseDto);
    }
}