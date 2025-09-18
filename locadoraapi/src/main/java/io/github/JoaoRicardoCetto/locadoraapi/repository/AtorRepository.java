package io.github.JoaoRicardoCetto.locadoraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.JoaoRicardoCetto.locadoraapi.model.Ator;
import java.util.UUID;

public interface AtorRepository extends JpaRepository<Ator, UUID> {
}
