package io.github.JoaoRicardoCetto.locadoraapi.repository;

import io.github.JoaoRicardoCetto.locadoraapi.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiretorRepository extends JpaRepository<Diretor, UUID>  {
}
