package io.github.JoaoRicardoCetto.locadoraapi.repository;

import io.github.JoaoRicardoCetto.locadoraapi.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClasseRepository extends JpaRepository<Classe, UUID>  {
}
