package io.github.JoaoRicardoCetto.locadoraapi.repository;

import io.github.JoaoRicardoCetto.locadoraapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID>  {
}
